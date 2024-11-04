package com.example.mlbweatherforecast

import com.example.mlbweatherforecast.utilities.DateFormatterUtility
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test



class DateFormatterUtilityUnitTests
{
    private lateinit var dateFormatterUtility : DateFormatterUtility

    @Before
    fun setUp(){
        dateFormatterUtility = DateFormatterUtility()
    }

    @Test
    fun testLongToString()
    {
        //arrange
        val unixTime : Long = 1730739219

        //act
        val dateString = dateFormatterUtility.convertUnixToReadableDate(unixTime)
        val expectedString = "Monday, November 4, 2024"
        //assert
        assertEquals(dateString, expectedString)
    }
}