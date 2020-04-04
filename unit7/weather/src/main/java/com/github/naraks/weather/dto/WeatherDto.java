package com.github.naraks.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WeatherDto {
    private MainDto main;
    @JsonProperty("cod")
    private String code;
    private String message;
}
