package com.github.naraks.weather;

import com.github.naraks.weather.api.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class WeatherServiceImplTest {

    @Autowired
    WeatherService weatherService;

    @Test
    void getCurrentWeatherByCity() {
        String weatherText = weatherService.getCurrentWeatherByCity("Krasnoyarsk");
        assertFalse(weatherText.isEmpty());
    }

    @Test
    void getCurrentWeatherByUnexistCity() {
        String weatherText = weatherService.getCurrentWeatherByCity("Pugstown");
        assertTrue(weatherText.contains("city not found"));
    }
}
