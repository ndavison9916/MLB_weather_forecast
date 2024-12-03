package com.example.mlbweatherforecast.di

import com.example.mlbweatherforecast.constants.BASE_URL_GEO
import com.example.mlbweatherforecast.constants.BASE_URL_ONE_CALL
import com.example.mlbweatherforecast.data.remote.ForecastApi
import com.example.mlbweatherforecast.data.remote.GeoZipAPI
import com.example.mlbweatherforecast.domain.utilities.ApiLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideForecastApi(): ForecastApi{
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiLoggingInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL_ONE_CALL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForecastApi::class.java)

    }

    @Provides
    @Singleton
    fun provideGeoZipApi(): GeoZipAPI{
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiLoggingInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL_GEO)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeoZipAPI::class.java)
    }
}