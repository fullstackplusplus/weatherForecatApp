package com.weather.weatherforecast.service;

import com.weather.weatherforecast.dto.WeatherResponse;
import com.weather.weatherforecast.client.OpenWeatherAPIClient;
import com.weather.weatherforecast.dto.OpenWeatherResponse;
import com.weather.weatherforecast.entity.UserInfo;
import com.weather.weatherforecast.entity.WeatherSearchHistory;
import com.weather.weatherforecast.repository.UserInfoRepository;
import com.weather.weatherforecast.repository.WeatherSearchHistoryProjection;
import com.weather.weatherforecast.repository.WeatherSearchHistoryRepository;
import com.weather.weatherforecast.util.WeatherResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    OpenWeatherAPIClient openWeatherAPIClient;

    @Autowired
    WeatherResponseConverter weatherResponseConverter;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    WeatherSearchHistoryRepository weatherSearchHistoryRepository;

    public WeatherResponse getWeatherData(String city, String username) {
        UserInfo userInfo = userInfoRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        OpenWeatherResponse openWeatherResponse = openWeatherAPIClient.getWeatherData(city);
        weatherSearchHistoryRepository.save(WeatherSearchHistory.builder()
                .city(city)
                .userInfo(userInfo)
                .build());
        return weatherResponseConverter.convertToWeatherResponse(openWeatherResponse);
    }

    public List<WeatherSearchHistoryProjection> getUserHistory(String username) {
        UserInfo userInfo = userInfoRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return weatherSearchHistoryRepository.findByUserInfo(userInfo).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
