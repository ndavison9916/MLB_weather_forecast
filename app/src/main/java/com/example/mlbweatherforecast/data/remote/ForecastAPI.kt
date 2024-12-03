package com.example.mlbweatherforecast.data.remote

import com.example.mlbweatherforecast.data.models.OneCallDataDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Class which constructs the API endpoint for retrieving the openweather one call forecast info
 */
interface ForecastApi {
    @GET("onecall")
    suspend fun getOneCallForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "imperial",
        @Query("exclude") exclude: String = "minutely"
    ): OneCallDataDto
}



