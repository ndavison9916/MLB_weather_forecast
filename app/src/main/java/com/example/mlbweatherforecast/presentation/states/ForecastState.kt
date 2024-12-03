package com.example.mlbweatherforecast.presentation.states

import com.example.mlbweatherforecast.dao.ForecastEntity
import com.example.mlbweatherforecast.data.models.CurrentForecast

/**
 * Current Forecast State
 */
data class ForecastState(
    val currentForecast: CurrentForecast? = null,
    val forecasts: List<ForecastEntity> = emptyList(),
    val isOnline: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)