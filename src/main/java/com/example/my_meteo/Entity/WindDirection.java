package com.example.my_meteo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindDirection {
    @JsonProperty("10m")
    protected float wind_dir_10m;
}
