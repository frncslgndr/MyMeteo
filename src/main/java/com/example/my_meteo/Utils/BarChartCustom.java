package com.example.my_meteo.Utils;

import com.example.my_meteo.Entity.InfoClimatResponse;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.ArrayList;

public class BarChartCustom {
    public BarChart createBarChart(InfoClimatResponse apiResponse, LocalDate date){

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setLayoutX(25);
        barChart.setLayoutY(235);
        barChart.setPrefHeight(200);
        barChart.setPrefWidth(500);
        barChart.getStyleClass().add("graph");

        barChart.setTitle("Précipitations (en mm)");

        XYChart.Series series = new XYChart.Series();
        series.setName("Evolution des précipitations");

        DateList dateList = new DateList();

        final ArrayList<String> list = dateList.dateList(apiResponse, date);

        // boucle sur chacune des heures du jour avec les bonnes données dans l'ordre chronologique
        for (String name : list) {
            series.getData().add(new XYChart.Data(
                    Integer.toString(apiResponse.getTimestampData().get(name).castDateToHour())+ "h",
                    apiResponse.getTimestampData().get(name).getPluie()));
        }

        barChart.getData().addAll(series);

        return barChart;
    }
}
