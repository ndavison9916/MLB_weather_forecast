package com.example.mlbweatherforecast.services

import com.example.mlbweatherforecast.responses.OneCallResponse
import com.example.mlbweatherforecast.constants.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ForecastAPIService {
    val client = OkHttpClient.Builder()
        .addInterceptor(ApiLoggingInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun create(): WeatherOneCallAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_ONE_CALL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
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
        @Query("units") units: String = "imperial",
        @Query("exclude") exclude: String = "minutely"
    ): OneCallResponse
}



