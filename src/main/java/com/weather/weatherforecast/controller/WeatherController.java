package com.weather.weatherforecast.controller;

import com.weather.weatherforecast.dto.WeatherResponse;
import com.weather.weatherforecast.entity.WeatherSearchHistory;
import com.weather.weatherforecast.repository.WeatherSearchHistoryProjection;
import com.weather.weatherforecast.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String city) {
        WeatherResponse weatherResponse = weatherService.getWeatherData(city, userDetails.getUsername());
        if (weatherResponse != null) {
            log.info("Weather Data is :- {}", weatherResponse);
            return ResponseEntity.ok(weatherResponse);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<WeatherSearchHistoryProjection>> getUserHistory(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(weatherService.getUserHistory(userDetails.getUsername()));
    }
}
