package com.example.mlbweatherforecast.responses

class OneCallResponse(
    val current: CurrentForecast,
    val daily: List<DailyForecast>
)

data class CurrentForecast(
    val temp: Double,
    val humidity: Int,
    val weather: List<WeatherDescription>,
    val wind_speed: Double
)

data class DailyForecast(
    val dt: Long,
    val temp: Temperature,
    val weather: List<WeatherDescription>,
    val humidity: Int,
    val wind_speed: Double,
    val wind_deg: Double,
    val summary: String,
    val pop: Double
)

data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class WeatherDescription(
    val main: String,
    val description: String
)