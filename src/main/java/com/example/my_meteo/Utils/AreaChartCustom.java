package com.example.my_meteo.Utils;

import com.example.my_meteo.Entity.InfoClimatResponse;
import javafx.geometry.Side;
import javafx.scene.chart.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class AreaChartCustom {
    public AreaChart createAreaChart(InfoClimatResponse apiResponse, LocalDate date){

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(950);
        yAxis.setMinorTickCount(2000);
        yAxis.setMinorTickCount(10);
        yAxis.setSide(Side.LEFT);
        yAxis.setTickUnit(20);
        yAxis.setUpperBound(1050);

        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setLayoutX(575);
        areaChart.setLayoutY(25);
        areaChart.setPrefHeight(203);
        areaChart.setPrefWidth(500);
        areaChart.getStyleClass().add("graph");

        areaChart.setTitle("Pression en hPa");

        XYChart.Series series = new XYChart.Series();
        series.setName("Evolution de la pression");

        DateList dateList = new DateList();

        final ArrayList<String> list = dateList.dateList(apiResponse, date);

        // boucle sur chacune des heures du jour avec les bonnes données dans l'ordre chronologique
        for (String name : list) {
            series.getData().add(new XYChart.Data(
                    Integer.toString(apiResponse.getTimestampData().get(name).castDateToHour())+ "h" ,
                    apiResponse.getTimestampData().get(name).getPression().getPression_niveau_de_la_mer()/100));
        }
        areaChart.getData().add(series);

        return areaChart;
    }
}
