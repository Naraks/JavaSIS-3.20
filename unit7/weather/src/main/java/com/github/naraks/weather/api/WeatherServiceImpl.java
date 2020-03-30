package com.github.naraks.weather.api;

import com.github.naraks.weather.CodeErrorsResponseHandler;
import com.github.naraks.weather.db.WeatherDataService;
import com.github.naraks.weather.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class WeatherServiceImpl implements WeatherService {

    public static final String URL = "https://community-open-weather-map.p.rapidapi.com/weather?units=metric&mode=json&q=%s";

    private final RestTemplate restTemplate;
    private final WeatherDataService weatherDataService;

    @Value("${x-rapidapi-key}")
    private String key;

    public WeatherServiceImpl(RestTemplate restTemplate, WeatherDataService weatherDataService) {
        this.restTemplate = restTemplate;
        this.weatherDataService = weatherDataService;
        restTemplate.setErrorHandler(new CodeErrorsResponseHandler());
    }

    @Override
    public String getCurrentWeatherByCity(String city) {
        String url = String.format(URL, city);
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
        headers.set("x-rapidapi-key", key);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<WeatherDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherDTO.class);
        checkResponse(response);
        String temp = response.getBody().getMain().getTemp();
        weatherDataService.save(city, temp, LocalDate.now());
        return temp;
    }

    private void checkResponse(ResponseEntity<WeatherDTO> response){
        if (response.getBody() == null || response.getBody().getMain() == null || response.getBody().getMain() == null) {
            throw new RuntimeException("Sorry, problems on the server. Try later");
        }
    }
}
