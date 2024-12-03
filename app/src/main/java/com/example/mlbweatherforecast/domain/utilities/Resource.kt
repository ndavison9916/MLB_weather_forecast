package com.example.mlbweatherforecast.domain.utilities

/**
 * Class to pass messages and data following a successful or failed API Call
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}