package com.example.mlbweatherforecast.services

import android.util.Log
import com.example.mlbweatherforecast.constants.TAG
import okhttp3.Interceptor
import okhttp3.Response

class ApiLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.i(TAG, "Request URL: ${request.url}")
        val response = chain.proceed(request)
        Log.i(TAG, "Response: ${response.message}")
        return chain.proceed(request)
    }
}