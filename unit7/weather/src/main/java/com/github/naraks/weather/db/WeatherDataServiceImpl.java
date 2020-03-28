package com.github.naraks.weather.db;

import com.github.naraks.weather.WeatherRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final JdbcTemplate jdbcTemplate;

    public WeatherDataServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(String city, String temp, LocalDate date) {
        jdbcTemplate.update("INSERT INTO weather (city, temp, date) VALUES (?, ?, ?)",
                city, temp, date);
    }

    @Override
    public List<WeatherRow> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM weather",
                (rs, rowNum) -> new WeatherRow(rs.getString("city"),
                        LocalDate.parse(rs.getString("date")), rs.getString("temp")));
    }

    @Override
    public List<WeatherRow> getByPeriodAndCity(String city, LocalDate startDate, LocalDate finishDate) {
        if (city.equals("all")) {
            return getByPeriod(startDate, finishDate);
        } else {
            return jdbcTemplate.query(
                    String.format("SELECT * FROM weather WHERE date BETWEEN '%s' AND '%s' AND city = '%s'",
                            java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(finishDate), city),
                    (rs, column) -> new WeatherRow(rs.getString("city"),
                            LocalDate.parse(rs.getString("date")), rs.getString("temp")));
        }
    }

    @Override
    public List<WeatherRow> getByPeriod(LocalDate startDate, LocalDate finishDate) {
        return jdbcTemplate.query(
                String.format("SELECT * FROM weather WHERE date BETWEEN '%s' AND '%s'", java.sql.Date.valueOf(startDate),
                        java.sql.Date.valueOf(finishDate)),
                (rs, column) -> new WeatherRow(rs.getString("city"),
                        LocalDate.parse(rs.getString("date")), rs.getString("temp")));
    }
}
