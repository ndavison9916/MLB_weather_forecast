package com.example.mlbweatherforecast.services

import com.example.mlbweatherforecast.responses.OneCallResponse
import com.example.mlbweatherforecast.responses.GeoZipResponse
import com.example.mlbweatherforecast.constants.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ForecastAPIService {
    fun create(): WeatherOneCallAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_ONE_CALL)
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON responses to Kotlin objects
            .build()
            .create(WeatherOneCallAPI::class.java)
    }
}
interface WeatherOneCallAPI {
    @GET("onecall")
    suspend fun getOneCallForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "standard",
        @Query("exclude") exclude: String = "minutely"
    ): OneCallResponse
}



