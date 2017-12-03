package server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dataset.UserVectorsProvider;
import jsoup.BodyParser;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public class Server {

    private static final UserVectorsProvider USER_VECTORS_PROVIDER = new UserVectorsProvider();
    private static final BodyParser QUESTION_PARSER = new BodyParser();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(10_001), 20);
        server.createContext("/find", Server::handle);
        server.createContext("/stop", httpExchange -> server.stop(0));
        server.start();
        System.out.println("Server started");
    }

    private static void handle(HttpExchange exchange) throws IOException {
        try (BufferedOutputStream out = new BufferedOutputStream(exchange.getResponseBody())) {
            String questionBody = QUESTION_PARSER.getQuestionBody(exchange.getRequestHeaders().getFirst("url"));

            Integer[] idNearests = findIdNearestUsers(calcVector(questionBody.toLowerCase()));

            String response = "{\r\n\tids=" + Arrays.stream(idNearests).map(Object::toString).collect(joining(",", "{", "}")) + "\r\n}\r\n";
            byte[] bytes = response.getBytes("UTF-8");

            exchange.sendResponseHeaders(200, bytes.length);
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.add("Content-Type", "application/json");
            responseHeaders.add("Content-Length", String.valueOf(bytes.length));
            out.write(bytes);
        }
    }

    private static Integer[] findIdNearestUsers(int[] questionVector) {
        System.out.println(activeVector(questionVector));
        Map<Integer, Double> coefficients = USER_VECTORS_PROVIDER.getUserVectors()
                                                                 .entrySet()
                                                                 .stream()
                                                                 .collect(toMap(Map.Entry::getKey, entry -> calcCoefficientNearest(entry.getValue(), questionVector)));
        return coefficients.entrySet()
                           .stream()
                           .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                           .limit(3)
                           .map(Map.Entry::getKey)
                           .peek(userId -> System.out.println(Arrays.toString(USER_VECTORS_PROVIDER.getUserVectors().get(userId))))
                           .peek(userId -> System.out.println(activeVector(USER_VECTORS_PROVIDER.getUserVectors().get(userId))))
                           .toArray(Integer[]::new);
    }

    private static double calcCoefficientNearest(int[] questionVector, int[] userVector) {
        int AB = 0;
        int sumA = 0;
        int sumB = 0;
        for(int i = 0; i < userVector.length; i++) {
            AB += userVector[i] * questionVector[i];
            sumA += userVector[i] * userVector[i];
            sumB += questionVector[i] * questionVector[i];
        }
        if (AB == 0) {
            return 0;
        }
        return AB / (Math.sqrt(sumA) * Math.sqrt(sumB));
    }

    private static Map<String, Integer> activeVector(int[] vector) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < vector.length; ++i) {
            if (vector[i] != 0) {
                result.put(UserVectorsProvider.SECTIONS.get(i), vector[i]);
            }
        }
        return result;
    }

    private static int[] calcVector(String question) {
        int[] vector = new int[UserVectorsProvider.SECTIONS.size()];
        for (int i = 0; i < vector.length; ++i) {
            vector[i] = getNumberOccurrences(question, UserVectorsProvider.SECTIONS.get(i), 0);
        }

        return vector;
    }

    private static int getNumberOccurrences(String source, String target, int index) {
        int result = source.indexOf(target, index);
        return result < 0 ? 0 : 1 + getNumberOccurrences(source, target, result + 1);
    }
}
