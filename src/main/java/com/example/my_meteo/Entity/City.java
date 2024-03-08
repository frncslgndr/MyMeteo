package com.example.my_meteo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class City {
    public City() {
        this.state = "unknown";
    }

    @JsonProperty("name")
    protected String city_name;

    protected String lat;

    protected String lon;

    protected String country;

    protected String state;

    public String getLatlong() {
        String _lat = this.lat;
        String _lon = this.lon;
        return _lat + ',' + _lon;
    }

    public boolean areLatLonProvided() {
        return lat != null && lon != null;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        City other = (City) obj;
        return city_name.equals(other.city_name) && state.equals(other.state);
    }

    @Override
    public int hashCode() {
        return city_name.hashCode() + state.hashCode();
    }

}
