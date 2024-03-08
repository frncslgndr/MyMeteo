package com.example.my_meteo.Entity;

import java.text.DateFormat;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast implements Comparable<Forecast>  {

    //Date et Heure de la prévision /!\ c'est le 1er niveau d'objet qu'on va récupérer de l'API
    protected LocalDateTime forecast_time;

    @JsonProperty("temperature")
    protected Temperature temperature;

    @JsonProperty("pression")
    protected Pression pression;

    @JsonProperty("pluie")
    protected Float pluie;

    @JsonProperty("pluie_convective")
    protected Float pluie_convective;

    @JsonProperty("vent_moyen")
    protected WindMedium wind_medium;

    @JsonProperty("vent_rafales")
    protected WindRafale wind_rafales;

    @JsonProperty("vent_direction")
    protected WindDirection wind_direction;

    @JsonProperty("iso_zero")
    protected int iso_zero;

    @JsonProperty("risque_neige")
    protected String risque_neige;

    @JsonProperty("cape")
    protected int cape;

    @JsonProperty("humidite")
    protected Humidity humidite;

    @JsonProperty("nebulosite")
    protected Nebulosity nebulosite;

    @Override
    public int compareTo(Forecast o) {
        return this.forecast_time.compareTo(o.getForecast_time());
    }

    public Integer castDateToHour() {
        return forecast_time.getHour();
    }

}
