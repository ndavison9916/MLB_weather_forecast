package com.example.mlbweatherforecast.services

import com.example.mlbweatherforecast.constants.BASE_URL_GEO
import com.example.mlbweatherforecast.responses.GeoZipResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object GeoZipAPIService {
    fun create(): WeatherGeoAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_GEO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherGeoAPI::class.java)
    }
}

interface WeatherGeoAPI {
    @GET("zip")
    suspend fun getLatLongByZip(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String,
    ): GeoZipResponse
}