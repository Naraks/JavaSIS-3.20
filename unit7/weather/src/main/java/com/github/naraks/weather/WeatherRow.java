package com.github.naraks.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class WeatherRow {
    private final String city;
    private final LocalDate date;
    private final String temp;
}
