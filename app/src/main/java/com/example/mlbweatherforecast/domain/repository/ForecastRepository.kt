package com.example.mlbweatherforecast.domain.repository

import com.example.mlbweatherforecast.domain.utilities.Resource
import com.example.mlbweatherforecast.data.models.GeoZipDataDto
import com.example.mlbweatherforecast.data.models.OneCallDataDto

/**
 * Interface for the API endpoints which fetches the location and forecast data
 */
interface ForecastRepository {
    suspend fun getLatLongByZip(zip: String): Resource<GeoZipDataDto>

    suspend fun getOneCallForecast(lat: Double, long: Double): Resource<OneCallDataDto>
}