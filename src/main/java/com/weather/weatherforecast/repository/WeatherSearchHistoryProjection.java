package com.weather.weatherforecast.repository;

import java.sql.Timestamp;

public interface WeatherSearchHistoryProjection {
    int getId();
    String getCity();
    Timestamp getCreatedAt();
}