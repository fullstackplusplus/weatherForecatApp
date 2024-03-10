package com.weather.weatherforecast.util;

import com.weather.weatherforecast.dto.OpenWeatherResponse;
import com.weather.weatherforecast.dto.WeatherResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Component
public class WeatherResponseConverter {

    public WeatherResponse convertToWeatherResponse(OpenWeatherResponse openWeatherResponse) {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity(openWeatherResponse.getName());
        weatherResponse.setTemperature(convertKelvinToCelsius(openWeatherResponse.getMain().getTemp()).concat("°C"));
        weatherResponse.setHumidity(openWeatherResponse.getMain().getHumidity() + "%");
        weatherResponse.setDescription(openWeatherResponse.getWeather().get(0).getDescription());
        weatherResponse.setWindSpeed(convertMilesToKilometers(openWeatherResponse.getWind().getSpeed()).concat("Km/h"));
        return weatherResponse;
    }

    public String convertKelvinToCelsius(double kelvin) {
        double celsius = kelvin - 273.15;
        DecimalFormat df = new DecimalFormat("#"); // Format to two decimal places
        return df.format(celsius);
    }

    public String convertMilesToKilometers(double miles) {
        double kilometer = miles * 1.60934;
        DecimalFormat df = new DecimalFormat("#.##"); // Format to two decimal places
        return df.format(kilometer);
    }
}
