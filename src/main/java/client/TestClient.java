package client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestClient {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://127.0.0.1:10000/find");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))) {
            writer.write("url\r\n");
            writer.write("https://stackoverflow.com/questions/47615775/android-studio-why-might-findviewbyid-crash-relativelayout-init-command");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            reader.readLine();
            reader.readLine();
            reader.readLine();
        }
    }

}
