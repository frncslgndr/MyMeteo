package com.example.my_meteo.Service;

import com.example.my_meteo.Entity.City;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GeoRequest {
    public static String API_URL = "http://api.openweathermap.org/geo/1.0/direct";

    protected String q;

    protected int limit = 1;

    protected String appid = "f019d77502606dfd15460639c30da36a";


    public GeoRequest(String cityName) {
        this.q = cityName;
    }

    public GeoRequest(String cityName, int limit){
        this.q = cityName;
        this.limit = limit;
    }

    public String generate_url() throws UnsupportedEncodingException {
        String encodedCityName = URLEncoder.encode(q, StandardCharsets.UTF_8);
        String url = String.format("%s?q=%s,FR&limit=%d&appid=%s", API_URL, encodedCityName, limit, appid);

        return url;
    }

    public List<City> getCities() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(generate_url()))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String datas = response.body();
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<City>> typeReference = new TypeReference<List<City>>() {};
            List<City> cities = mapper.readValue(datas, typeReference);
            List<City> filteredCities = cities.stream()
                    .filter(City::areLatLonProvided)
                    .toList();

            List<City> uniqueCities = new ArrayList<>(new HashSet<>(filteredCities));

            return uniqueCities;
        } else {
            throw new RuntimeException("HTTP Request Failed with status code: " + response.statusCode());
        }
    }

    public City getCity() throws IOException, InterruptedException {
        return getCities().getFirst();
    }

}
