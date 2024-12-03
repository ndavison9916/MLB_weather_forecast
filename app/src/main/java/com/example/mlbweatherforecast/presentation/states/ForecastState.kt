package com.example.mlbweatherforecast.presentation.states

import com.example.mlbweatherforecast.data.models.CurrentForecast

/**
 * Current Forecast State
 */
data class ForecastState(
    val currentForecast: CurrentForecast? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)