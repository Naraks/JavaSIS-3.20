package com.github.naraks.weather.formatter;

import com.github.naraks.weather.WeatherRow;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShellFormatterServiceImpl implements FormatterService{

    public String getAllRecords(List<WeatherRow> list) {
        return list.stream()
                .map(o -> String.format("%s %s temp: %s°С", o.getCity(), o.getDate(), o.getTemp()))
                .collect(Collectors.joining("\n"));
    }

    public String getAverageTemperatureByMonthAndCity(List<WeatherRow> list) {
        Map<String, Map<Month, Double>> map = list.stream()
                .collect(Collectors.groupingBy(WeatherRow::getCity,
                        Collectors.groupingBy(row -> row.getDate().getMonth(),
                                Collectors.averagingDouble(t -> Double.parseDouble(t.getTemp())))));
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Map<Month, Double>> m1 : map.entrySet()) {
            sb.append(m1.getKey()).append("\n");
            for (Map.Entry<Month, Double> m2 : m1.getValue().entrySet()) {
                sb.append(m2.getKey()).append(": ").append(m2.getValue()).append("°C");
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
