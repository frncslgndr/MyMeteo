package com.example.my_meteo.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Map;

import com.example.my_meteo.Entity.InfoClimatResponse;
import com.example.my_meteo.Entity.Forecast;
import com.fasterxml.jackson.databind.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InfoClimatRequest {
    public static String API_URL = "https://www.infoclimat.fr/public-api/gfs/json?";

    protected String _auth;

    protected String _latlong;

    protected String _c;

    public InfoClimatRequest() {
        _auth = "AhgCFVIsBiRecwYxB3EBKFM7UGVZLwIlBnoHZA5gA34CZQRgVjIEZVI4UC1Qf1FgBCkCYl5kVGkFZAprCHpeIgJjAm9SOAZhXjIGbAcwASpTf1AtWWcCJQZ6B2EOawNoAn8EYlY2BGdSI1AwUGlRewQyAn1eflRtBWEKbQhsXjoCYAJlUjUGbV41BnsHKAEzU2VQZVluAjgGbQdlDmUDYgJnBDRWPARlUj5QLFBiUWwEMgJlXmJUbQVvCm0Iel4iAhgCFVIsBiRecwYxB3EBKFM1UG5ZMg%3D%3D";
        _c = "624be648dd331298b057a6909eef0ace";
        _latlong = "43.40735,5.05526";
    }

    public InfoClimatRequest(String latlong) {
        _auth = "AhgCFVIsBiRecwYxB3EBKFM7UGVZLwIlBnoHZA5gA34CZQRgVjIEZVI4UC1Qf1FgBCkCYl5kVGkFZAprCHpeIgJjAm9SOAZhXjIGbAcwASpTf1AtWWcCJQZ6B2EOawNoAn8EYlY2BGdSI1AwUGlRewQyAn1eflRtBWEKbQhsXjoCYAJlUjUGbV41BnsHKAEzU2VQZVluAjgGbQdlDmUDYgJnBDRWPARlUj5QLFBiUWwEMgJlXmJUbQVvCm0Iel4iAhgCFVIsBiRecwYxB3EBKFM1UG5ZMg%3D%3D";
        _c = "624be648dd331298b057a6909eef0ace";
        _latlong = latlong;
    }

    public String generate_url() {
        return API_URL + "_ll=" + _latlong + "&" + "_auth=" + _auth + "&" + "_c=" + _c ;
    }

    public InfoClimatResponse send() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(generate_url()))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String datas = response.body();
            ObjectMapper mapper = new ObjectMapper();
            InfoClimatResponse infoClimatResponse = mapper.readValue(datas, InfoClimatResponse.class);

            Map<String, Forecast> timestampData = infoClimatResponse.getTimestampData();
            for (Map.Entry<String, Forecast> entry : timestampData.entrySet()) {
                String timestamp = entry.getKey();
                Forecast dataPoint = entry.getValue();

                String pattern = "yyyy-MM-dd HH:mm:ss";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);

                dataPoint.setForecast_time(dateTime);
            }

            return infoClimatResponse;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());
            String message = jsonNode.get("message").asText();
            throw new Exception(message);
        }
    }

}
