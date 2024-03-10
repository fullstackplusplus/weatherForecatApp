package com.weather.weatherforecast.repository;

import com.weather.weatherforecast.entity.LoginInfo;
import com.weather.weatherforecast.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Integer> {
    Optional<LoginInfo> findByUsername(String username);

    LoginInfo findByToken(String token);
}