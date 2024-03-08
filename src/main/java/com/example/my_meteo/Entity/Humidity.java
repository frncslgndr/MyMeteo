package com.example.my_meteo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Humidity {
    @JsonProperty("2m")
    protected float hum_2m;
}
