package com.example.mlbweatherforecast.data.repository

import com.example.mlbweatherforecast.BuildConfig
import com.example.mlbweatherforecast.data.remote.ForecastApi
import com.example.mlbweatherforecast.data.remote.GeoZipAPI
import com.example.mlbweatherforecast.domain.repository.ForecastRepository
import com.example.mlbweatherforecast.domain.utilities.Resource
import com.example.mlbweatherforecast.data.models.GeoZipDataDto
import com.example.mlbweatherforecast.data.models.OneCallDataDto
import javax.inject.Inject

/**
 * class which implements the API interface
 */
class ForecastRepositoryImpl @Inject constructor (
    private val oneCallAPI: ForecastApi,
    private val geoAPI: GeoZipAPI
): ForecastRepository {

    override suspend fun getLatLongByZip(zip: String): Resource<GeoZipDataDto> {
        return try {
            Resource.Success(
                data = geoAPI.getLatLongByZip(
                    zip = zip,
                    apiKey = BuildConfig.OPENWEATHER_API_KEY
                )
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "unknown error")
        }
    }

    override suspend fun getOneCallForecast(lat: Double, long: Double): Resource<OneCallDataDto> {
        return try {
            Resource.Success(
                data = oneCallAPI.getOneCallForecast(
                    latitude = lat,
                    longitude = long,
                    apiKey = BuildConfig.OPENWEATHER_API_KEY,
                    units = "imperial",
                    exclude = "minutely"
                )
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "unknown error")
        }
    }
}