package com.example.mlbweatherforecast.data

data class ForecastData(
    val date: String,
    val temperature: Double,
    val description: String,
    val wind: String
)