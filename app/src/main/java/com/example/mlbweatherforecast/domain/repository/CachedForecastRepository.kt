package com.example.mlbweatherforecast.domain.repository

import com.example.mlbweatherforecast.dao.ForecastEntity

/**
 * Interface for the repository which will interact with the Dao
 */
interface CachedForecastRepository {

    suspend fun deleteForecasts()

    suspend fun addForecasts(forecasts: List<ForecastEntity>)

    suspend fun fetchWeatherFromDatabase(): List<ForecastEntity>?

}