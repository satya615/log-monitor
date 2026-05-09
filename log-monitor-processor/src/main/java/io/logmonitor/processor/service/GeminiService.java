package io.logmonitor.processor.service;


import io.logmonitor.processor.model.LogEvent;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeminiService {

    private final String API_KEY = "AIzaSyBcZwOuQM-GJ4zGctzcV-Hne8Sx5JSKS5s";

    public String analyze(LogEvent event) {

        try {

            String prompt = """
                    Analyze this backend error log.

                    Return:
                    1. Root Cause
                    2. Severity
                    3. Suggested Fix

                    Log:
                    """ + event.getMessage() +
                    "\n" + event.getStackTrace();

            String body = """
            {
              "contents": [{
                "parts": [{
                  "text": "%s"
                }]
              }]
            }
            """.formatted(prompt);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
                                    + API_KEY
                    ))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response =
                    HttpClient.newHttpClient()
                            .send(
                                    request,
                                    HttpResponse.BodyHandlers.ofString()
                            );

            return response.body();

        } catch (Exception e) {
            return "AI analysis failed";
        }
    }
}
