package com.github.naraks.weather.dto;

import lombok.Getter;

@Getter
public class MainDTO {
    private String temp;
    private String feelsLike;
    private String temp_min;
    private String temp_max;
    private String pressure;
    private String humidity;
}
