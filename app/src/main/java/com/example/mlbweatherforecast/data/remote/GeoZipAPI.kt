package com.example.mlbweatherforecast.data.remote

import com.example.mlbweatherforecast.data.models.GeoZipDataDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Class which constructs the API endpoint for retrieving the openweather geolocation info
 */
interface GeoZipAPI {
    @GET("zip")
    suspend fun getLatLongByZip(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String,
    ): GeoZipDataDto
}