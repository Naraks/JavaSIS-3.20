package com.github.naraks.weather.db;

import com.github.naraks.weather.WeatherRow;

import java.time.LocalDate;
import java.util.List;

public interface WeatherDataService {
    void save(String city, String temp, LocalDate date);
    List<WeatherRow> getAll();
    List<WeatherRow> getByPeriod(String city, LocalDate startDate, LocalDate finishDate);
    List<WeatherRow> getByPeriod(LocalDate startDate, LocalDate finishDate);
}
