package com.weather.weatherforecast.client;

import com.weather.weatherforecast.dto.OpenWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class OpenWeatherAPIClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${openweather.api.key}")
    private String apiKey;

    public OpenWeatherResponse getWeatherData(String city) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city
                + "&appid=" + apiKey;
        ResponseEntity<OpenWeatherResponse> response = restTemplate.getForEntity(url, OpenWeatherResponse.class);
        log.info("Weather data for city: " + response.getBody());
        return response.getBody();
    }

}
