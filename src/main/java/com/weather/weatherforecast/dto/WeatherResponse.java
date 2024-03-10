package com.weather.weatherforecast.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private String city;
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String description;
}
