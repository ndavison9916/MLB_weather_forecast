package com.example.mlbweatherforecast.domain.utilities

/**
 * Interface for the Network Checker
 */
interface NetworkChecker {
    fun isConnected(): Boolean
}