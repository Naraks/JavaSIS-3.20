package com.github.naraks.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MainDto {
    private String temp;
    private String feelsLike;
    @JsonProperty("temp_min")
    private String temMin;
    @JsonProperty("temp_max")
    private String temMax;
    private String pressure;
    private String humidity;
}
