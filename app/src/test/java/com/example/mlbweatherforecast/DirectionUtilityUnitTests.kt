package com.example.mlbweatherforecast

import com.example.mlbweatherforecast.domain.utilities.DirectionUtility
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class DirectionUtilityUnitTests {
    private lateinit var directionFormatter : DirectionUtility

    @Before
    fun setUp(){
        directionFormatter = DirectionUtility()
    }

    @Test
    fun testDegreeToDirection()
    {
        //arrange
        val degree : Double = 299.0

        //act
        val dateString = directionFormatter.degreesToCompassDirection(degree)
        val expectedString = "WNW"

        //assert
        assertEquals(dateString, expectedString)
    }
}