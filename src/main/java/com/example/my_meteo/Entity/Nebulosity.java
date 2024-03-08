package com.example.my_meteo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nebulosity {
    @JsonProperty("haute")
    protected int haute;
    @JsonProperty("moyenne")
    protected int moyenne;
    @JsonProperty("basse")
    protected int basse;
    @JsonProperty("totale")
    protected int totale;
}
