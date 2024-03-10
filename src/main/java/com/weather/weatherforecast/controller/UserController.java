package com.weather.weatherforecast.controller;

import com.weather.weatherforecast.dto.AuthenticationRequestDto;
import com.weather.weatherforecast.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> userRegistration(@RequestBody @Valid AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok(userService.addUser(authenticationRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok(userService.loginUser(authenticationRequestDto));
    }

    @PostMapping("/logout-user")
    public ResponseEntity<String> logoutUser(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok("User logged out successfully");
    }
}
