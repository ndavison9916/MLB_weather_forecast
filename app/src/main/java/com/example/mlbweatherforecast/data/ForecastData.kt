package com.example.mlbweatherforecast.data

data class ForecastData(
    val date: String,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val windSpeed: Double,
    val windDirection: String
)