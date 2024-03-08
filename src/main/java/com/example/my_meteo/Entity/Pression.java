package com.example.my_meteo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pression {

    @JsonProperty("niveau_de_la_mer")
    protected int pression_niveau_de_la_mer;

}
