package com.example.my_meteo.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TouitRequest {

    public static String API_URL = "";

    public static void SendTouit(String name, String message) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = API_URL + "send";
        String requestBody = "name="+name+"&message="+message;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

}
