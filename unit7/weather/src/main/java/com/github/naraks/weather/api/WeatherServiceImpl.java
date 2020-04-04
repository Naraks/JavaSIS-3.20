package com.github.naraks.weather.api;

import com.github.naraks.weather.CodeErrorsResponseHandler;
import com.github.naraks.weather.db.WeatherDataService;
import com.github.naraks.weather.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherDataService weatherDataService;

    @Value("${x-rapidapi-key}")
    private String key;

    @Value("${x-rapidapi-host}")
    private String host;

    @Value("${weather_url}")
    private String weatherUrl;

    public WeatherServiceImpl(RestTemplate restTemplate,
                              WeatherDataService weatherDataService) {
        this.restTemplate = restTemplate;
        this.weatherDataService = weatherDataService;
        restTemplate.setErrorHandler(new CodeErrorsResponseHandler());
    }

    @Override
    public String getCurrentWeatherByCity(String city) {
        String url = String.format(weatherUrl, city);
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", host);
        headers.set("x-rapidapi-key", key);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<WeatherDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherDto.class);
        checkResponse(response);
        String temp = response.getBody().getMain().getTemp();
        weatherDataService.save(city, temp, LocalDate.now());
        return temp;
    }

    private void checkResponse(ResponseEntity<WeatherDto> response){
        if (response.getBody() == null
                || response.getBody().getMain() == null
                || response.getBody().getMain().getTemp() == null) {
            throw new RuntimeException("Sorry, problems on the server. Try later");
        }
    }
}
