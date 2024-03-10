package com.weather.weatherforecast.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleMissingParameterException(MissingServletRequestParameterException ex) {
        HashMap<String, String> response = new HashMap<>();
        response.put("errorMessage", ex.getLocalizedMessage());
        return response;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        HashMap<String, String> response = new HashMap<>();
        response.put("errorMessage", ex.getLocalizedMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleException(Exception ex) {
        HashMap<String, String> response = new HashMap<>();
        response.put("errorMessage", ex.getLocalizedMessage());
        return response;
    }

    
}
