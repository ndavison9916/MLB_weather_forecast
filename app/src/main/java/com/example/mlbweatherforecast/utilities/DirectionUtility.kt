package com.example.mlbweatherforecast.utilities

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