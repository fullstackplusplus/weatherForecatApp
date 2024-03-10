package com.weather.weatherforecast.dto;

import lombok.Data;

@Data
public class Main{
	private double temp;
	private Object tempMin;
	private int grndLevel;
	private int humidity;
	private int pressure;
	private int seaLevel;
	private double feelsLike;
	private double tempMax;
}