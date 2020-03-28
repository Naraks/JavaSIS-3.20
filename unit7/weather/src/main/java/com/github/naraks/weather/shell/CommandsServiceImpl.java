package com.github.naraks.weather.shell;

import com.github.naraks.weather.ShellFormatterService;
import com.github.naraks.weather.api.WeatherService;
import com.github.naraks.weather.db.WeatherDataService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@ShellComponent
public class CommandsServiceImpl implements CommandsService {
    private final WeatherService weatherService;
    private final WeatherDataService weatherDataService;
    private final ShellFormatterService shellFormatterService;

    public CommandsServiceImpl(WeatherService weatherService, WeatherDataService weatherDataService, ShellFormatterService shellFormatterService) {
        this.weatherService = weatherService;
        this.weatherDataService = weatherDataService;
        this.shellFormatterService = shellFormatterService;
    }

    @Override
    @ShellMethod("Show current temperature in da city")
    public String temp(@ShellOption(defaultValue = "Krasnoyarsk") String city) {
        String temp = weatherService.getCurrentWeatherByCity(city);
        return String.format("Current temperature (°C) in city %s: %s", city, temp);
    }

    @Override
    @ShellMethod("Show history of requests")
    public String showAll() {
        return shellFormatterService.getAllRecords(weatherDataService.getAll());
    }

    @Override
    @ShellMethod("Show temperature by city, start date and finish date. Example \"avg-temp Krasnoyarsk 2020-01-01 2020-12-31\"")
    public String avgTemp(@ShellOption(defaultValue = "all") String city,
                          @ShellOption(defaultValue = "1900-01-01") String startDate,
                          @ShellOption(defaultValue = "2100-01-01") String finishDate) {
        try {
            LocalDate sDate = LocalDate.parse(startDate);
            LocalDate fDate = LocalDate.parse(finishDate);
            return shellFormatterService.getRecordsByPeriodAndCity(weatherDataService.getByPeriodAndCity(city, sDate, fDate));
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Incorrect date format. Use 'yyyy-MM-dd'");
        }
    }
}


