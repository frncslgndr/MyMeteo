package com.example.my_meteo.Controller;

import com.example.my_meteo.Entity.City;
import com.example.my_meteo.Entity.InfoClimatResponse;
import lombok.Getter;
import lombok.Setter;

import javafx.stage.Stage;

@Getter
@Setter
public final class SuperController {
    private static SuperController instance;

    public City current_city;

    public Stage stage;

    public InfoClimatResponse infoClimatResponse;

    private SuperController() {

    }

    public static SuperController getInstance() {
        if (instance == null) {
            instance = new SuperController();
        }
        return instance;
    }
}
