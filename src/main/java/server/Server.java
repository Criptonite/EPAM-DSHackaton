package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dataset.UserVectorsProvider;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Comparator;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Server {

    private static final UserVectorsProvider USER_VECTORS_PROVIDER = new UserVectorsProvider();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(10_000), 10);
        server.createContext("/find", Server::handle);
        server.start();
        server.createContext("/stop", httpExchange -> server.stop(0));
    }

    private static void handle(HttpExchange exchange) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(exchange.getResponseBody()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            String type = reader.readLine();
            int[] questionVector = null;
            if ("url".equals(type)) {
                // TODO извлечь тело
            } else {
                questionVector = calcVector(reader.readLine());
            }

            Integer[] idNearests = findIdNearestUsers(questionVector);
            for (int userId : idNearests) {
                writer.write(String.valueOf(userId) + "\r\n");
            }
        }
    }

    private static Integer[] findIdNearestUsers(int[] questionVector) {
        Map<Integer, Double> coefficients = USER_VECTORS_PROVIDER.getUserVectors()
                                                                 .entrySet()
                                                                 .stream()
                                                                 .collect(toMap(Map.Entry::getKey, entry -> calcCoefficientNearest(entry.getValue(), questionVector)));
        return coefficients.entrySet()
                           .stream()
                           .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                           .limit(3)
                           .map(Map.Entry::getKey)
                           .toArray(Integer[]::new);
    }

    public static double calcCoefficientNearest(int[] userVector, int[] questionVector) {
        int AB = 0;
        int sumA = 0;
        int sumB = 0;
        for(int i = 0; i < userVector.length; i++) {
            AB += userVector[i] * questionVector[i];
            sumA += userVector[i] * userVector[i];
            sumB += questionVector[i] * questionVector[i];
        }
        return AB / (Math.sqrt(sumA) * Math.sqrt(sumB));
    }

    private static int[] calcVector(String question) {
        int[] vector = new int[UserVectorsProvider.SECTIONS.size()];
        for (int i = 0; i < vector.length; ++i) {
            vector[i] = question.contains(UserVectorsProvider.SECTIONS.get(i)) ? 1 : 0;
        }
        return vector;
    }
}
