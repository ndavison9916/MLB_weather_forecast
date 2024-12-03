package com.example.mlbweatherforecast.data.repository

import android.app.Application
import com.example.mlbweatherforecast.dao.AppDatabase
import com.example.mlbweatherforecast.dao.ForecastEntity
import com.example.mlbweatherforecast.domain.repository.CachedForecastRepository
import javax.inject.Inject

/**
 * Class which interacts with Dao for cached forecast data
 */
class CachedForecastRepositoryImpl @Inject constructor (
    application: Application
): CachedForecastRepository {

    private val database = AppDatabase.getInstance(application.applicationContext)

    override suspend fun deleteForecasts() {
        database?.forecastDao()?.deleteAllForecasts()
    }

    override suspend fun addForecasts(forecasts: List<ForecastEntity>) {
        database?.forecastDao()?.insertAll(forecasts)
    }

    override suspend fun fetchWeatherFromDatabase(): List<ForecastEntity>? {
        return database?.forecastDao()?.getAll()
    }
}