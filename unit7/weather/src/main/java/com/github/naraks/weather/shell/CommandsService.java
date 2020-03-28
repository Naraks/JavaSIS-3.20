package com.github.naraks.weather.shell;

public interface CommandsService {
    String temp(String city);

    String showAll();

    String avgTemp(String city, String startDate, String finishDate);
}
