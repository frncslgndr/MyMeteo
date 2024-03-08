package com.example.my_meteo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindRafale {
    @JsonProperty("10m")
    protected float wind_raf_10m;
}
