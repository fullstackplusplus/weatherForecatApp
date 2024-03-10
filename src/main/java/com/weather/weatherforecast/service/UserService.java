package com.weather.weatherforecast.service;

import com.weather.weatherforecast.dto.AuthenticationRequestDto;
import com.weather.weatherforecast.entity.LoginInfo;
import com.weather.weatherforecast.entity.UserInfo;
import com.weather.weatherforecast.repository.LoginInfoRepository;
import com.weather.weatherforecast.repository.UserInfoRepository;
import com.weather.weatherforecast.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserInfoRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginInfoRepository loginInfoRepository;


    public String addUser(AuthenticationRequestDto authenticationRequestDto) {
        if (!isUsernameUnique(authenticationRequestDto.getUsername())) {
            return "username already exists";
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(authenticationRequestDto.getUsername());
        userInfo.setPassword(passwordEncoder.encode(authenticationRequestDto.getPassword()));
        UserInfo savedUser = repository.save(userInfo);
        log.info("user added to system successfully with username {} and password {}", savedUser.getUsername(), savedUser.getPassword());
        return "user added to system successfully";
    }

    public String loginUser(AuthenticationRequestDto authenticationRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(authenticationRequestDto.getUsername());
            LoginInfo loginInfo = LoginInfo.builder()
                    .username(authenticationRequestDto.getUsername())
                    .token(token)
                    .isBlackListed(false).build();
            loginInfoRepository.save(loginInfo);
            return token;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    public boolean isUsernameUnique(String username) {
        return repository.findByUsername(username).isEmpty();
    }
}
