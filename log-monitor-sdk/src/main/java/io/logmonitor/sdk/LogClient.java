package io.logmonitor.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogClient {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void send(String serverUrl, LogEvent event) {

        try {

            URL url = new URL(serverUrl);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String json = mapper.writeValueAsString(event);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes());
            }

            connection.getResponseCode();

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}