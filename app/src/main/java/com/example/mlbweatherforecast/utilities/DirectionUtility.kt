package com.example.mlbweatherforecast.utilities

/**
 * Utility class to convert the wind direction degree to a readable compass direction
 */
class DirectionUtility {
    private val directions = arrayOf(
        "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
        "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
    )
    fun degreesToCompassDirection(degrees: Double): String {
        val index = ((degrees % 360) / 22.5).toInt()
        return directions[index]
    }
}