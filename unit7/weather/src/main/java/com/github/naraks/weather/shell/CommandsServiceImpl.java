package com.github.naraks.weather.shell;

import com.github.naraks.weather.formatter.ShellFormatterServiceImpl;
import com.github.naraks.weather.api.WeatherService;
import com.github.naraks.weather.db.WeatherDataService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@ShellComponent
public class CommandsServiceImpl implements CommandsService {

    public static final String ALL = "all";

    private final WeatherService weatherService;
    private final WeatherDataService weatherDataService;
    private final ShellFormatterServiceImpl shellFormatterServiceImpl;

    public CommandsServiceImpl(WeatherService weatherService, WeatherDataService weatherDataService, ShellFormatterServiceImpl shellFormatterServiceImpl) {
        this.weatherService = weatherService;
        this.weatherDataService = weatherDataService;
        this.shellFormatterServiceImpl = shellFormatterServiceImpl;
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
        return shellFormatterServiceImpl.getAllRecords(weatherDataService.getAll());
    }

    @Override
    @ShellMethod("Show temperature by city, start date and finish date. Example " +
            "\"avg-temp Krasnoyarsk 2020-01-01 2020-12-31\"")
    public String avgTemp(@ShellOption(defaultValue = ALL) String city,
                          @ShellOption(defaultValue = "1900-01-01") String startDate,
                          @ShellOption(defaultValue = "2100-01-01") String finishDate) {
        return shellFormatterServiceImpl.getRecordsByPeriodAndCity(weatherDataService.getByPeriod(city,
                parseDate(startDate), parseDate(finishDate)));
    }

    private LocalDate parseDate(String startDate){
        try {
            return LocalDate.parse(startDate);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Incorrect date format. Use 'yyyy-MM-dd'");
        }
    }
}


