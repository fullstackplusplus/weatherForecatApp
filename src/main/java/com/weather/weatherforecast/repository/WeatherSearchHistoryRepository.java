package com.weather.weatherforecast.repository;

import com.weather.weatherforecast.entity.UserInfo;
import com.weather.weatherforecast.entity.WeatherSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherSearchHistoryRepository extends JpaRepository<WeatherSearchHistory, Integer> {
    Optional<List<WeatherSearchHistoryProjection>> findByUserInfo(UserInfo userInfo);

}