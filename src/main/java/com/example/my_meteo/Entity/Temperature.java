package com.example.my_meteo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Temperature {

    @JsonProperty("2m")
    protected Float temp_2m;

    @JsonProperty("sol")
    protected Float temp_sol;

    @JsonProperty("500hPa")
    protected Float temp_500hPa;

    @JsonProperty("850hPa")
    protected Float temp_850hPa;


}
