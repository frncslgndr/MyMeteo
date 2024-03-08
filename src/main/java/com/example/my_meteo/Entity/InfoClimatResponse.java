package com.example.my_meteo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class InfoClimatResponse {
    @JsonProperty("request_state")
    private int requestState;

    @JsonProperty("request_key")
    private String requestKey;

    @JsonProperty("message")
    private String message;

    @JsonProperty("model_run")
    private String modelRun;

    @JsonProperty("source")
    private String source;

    private Map<String, Forecast> timestampData = new HashMap<>();

    @JsonAnySetter
    public void addTimestampData(String timestamp, Forecast dataPoint) {
        timestampData.put(timestamp, dataPoint);
    }

    public Map<String, Forecast> getTimestampData() {
        return timestampData;
    }

    public Map<String, Forecast> getSortedForecasts() {
        return timestampData.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((f1, f2) -> f1.getForecast_time().compareTo(f2.getForecast_time())))
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        )
                );
    }

    public Map<String, Forecast> getForecastsByDate(LocalDate localDate) {
        Map<String, Forecast> filteredMap = timestampData.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getForecast_time().toLocalDate().equals(localDate))
                .sorted(Map.Entry.comparingByValue((f1, f2) -> f1.getForecast_time().compareTo(f2.getForecast_time())))
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        )
                );

        return filteredMap;
    }

    public Forecast getClosestForecastByDate(LocalDate localDate) {
        return timestampData.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getForecast_time().toLocalDate().equals(localDate))
                .min(Map.Entry.comparingByValue(Comparator.comparing(f -> Math.abs(ChronoUnit.MINUTES.between(localDate.atStartOfDay(), f.getForecast_time())))))
                .map(Map.Entry::getValue)
                .orElse(null);  // You may want to handle the case where no matching forecast is found
    }

    public Forecast getMaxTemperatureForecastByDate(LocalDate localDate) {
        return timestampData.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getForecast_time().toLocalDate().equals(localDate))
                .max(Comparator.comparingDouble(entry -> entry.getValue().getTemperature().getTemp_2m()))
                .map(Map.Entry::getValue)
                .orElse(null);  // You may want to handle the case where no matching forecast is found
    }

}
