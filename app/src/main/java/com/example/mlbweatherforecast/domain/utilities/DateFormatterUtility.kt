package com.example.mlbweatherforecast.domain.utilities

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Utility class to convert a unixTime UTC Long to a readable String
 */
class DateFormatterUtility {
    fun convertUnixToReadableDate(unixTime: Long): String {
        val date = Date((unixTime * 1000))
        val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }
}