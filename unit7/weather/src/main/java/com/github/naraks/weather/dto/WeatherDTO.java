package com.github.naraks.weather.dto;

import lombok.Getter;

@Getter
public class WeatherDTO {
    private MainDTO main;
    private String cod;
    private String message;
}
