package com.github.naraks.weather.formatter;

import com.github.naraks.weather.WeatherRow;

import java.util.List;

public interface FormatterService {
    String getAllRecords(List<WeatherRow> list);
    String getRecordsByPeriodAndCity(List<WeatherRow> list);
}
