package com.github.naraks.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void onPostConstruct() {
        jdbcTemplate.update(
                "CREATE TABLE IF NOT EXISTS weather (" +
                        "city text NOT NULL," +
                        "temp text NOT NULL, " +
                        "date DATE NOT NULL" +
                        ");");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
