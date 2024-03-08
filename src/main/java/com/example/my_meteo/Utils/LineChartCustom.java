package com.example.my_meteo.Utils;

import com.example.my_meteo.Entity.InfoClimatResponse;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javafx.scene.chart.NumberAxis;

public class LineChartCustom {
    public LineChart createLineChart(InfoClimatResponse apiResponse, LocalDate date){

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setLayoutX(25);
        lineChart.setLayoutY(25);
        lineChart.setPrefHeight(200);
        lineChart.setPrefWidth(500);
        lineChart.getStyleClass().add("graph");

        lineChart.setTitle("Température (en °C)");

        XYChart.Series series = new XYChart.Series();
        series.setName("Evolution de la température");

        DateList dateList = new DateList();

        final ArrayList<String> list = dateList.dateList(apiResponse, date);

        // boucle sur chacune des heures du jour avec les bonnes données dans l'ordre chronologique
        for (String name : list) {
            series.getData().add(new XYChart.Data(
                    Integer.toString(apiResponse.getTimestampData().get(name).castDateToHour())+ "h",
                    apiResponse.getTimestampData().get(name).getTemperature().getTemp_2m()-273.15));
        }

        lineChart.getData().add(series);

        return lineChart;
    }
}
