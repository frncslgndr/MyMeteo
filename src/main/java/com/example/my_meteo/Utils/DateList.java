package com.example.my_meteo.Utils;

import com.example.my_meteo.Entity.InfoClimatResponse;

import java.time.LocalDate;
import java.util.ArrayList;

public class DateList {

    public ArrayList<String> dateList (InfoClimatResponse apiResponse, LocalDate date) {
        // création d'une liste avec les dates du jour choisi
        final ArrayList<String> list = new ArrayList<>();
        apiResponse.getForecastsByDate(date).forEach((k,v)->{
            list.add(k);
        });

        return list;
    }
}
