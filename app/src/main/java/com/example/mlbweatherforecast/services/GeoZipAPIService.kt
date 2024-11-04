package com.example.mlbweatherforecast.services

import com.example.mlbweatherforecast.constants.BASE_URL_GEO
import com.example.mlbweatherforecast.responses.GeoZipResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Class which constructs the API service for retrieving the openweather one call geolocation info
 */
object GeoZipAPIService {
    val client = OkHttpClient.Builder()
        .addInterceptor(ApiLoggingInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun create(): WeatherGeoAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_GEO)
            .client(client)
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