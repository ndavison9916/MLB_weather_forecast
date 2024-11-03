package com.example.mlbweatherforecast.utilities

import android.util.Log
import com.example.mlbweatherforecast.BuildConfig
import com.example.mlbweatherforecast.constants.TAG
import com.example.mlbweatherforecast.data.ForecastData
import com.example.mlbweatherforecast.responses.GeoZipResponse
import com.example.mlbweatherforecast.services.WeatherGeoAPI
import com.example.mlbweatherforecast.services.WeatherOneCallAPI

class ForecastUtility(val oneCallAPI : WeatherOneCallAPI, val geoZipAPI : WeatherGeoAPI) {
    suspend fun getCoordinatesByZip(zipCode: String): GeoZipResponse? {
        return try {
            val response = geoZipAPI.getLatLongByZip(zipCode, BuildConfig.OPENWEATHER_API_KEY)
            return response
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            null
        }
    }
}