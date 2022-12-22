package com.pacmac.weather

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.pacmac.weather.WeatherSearchUI.WeatherItem
import com.pacmac.weather.model.WeatherModel
import org.junit.Rule
import org.junit.Test

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-22
 */

internal class WeatherItemUIKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    private val time = 1671741336000
    private val timeString = "12-35-36"
    private val temperature = -5.0f
    private val model = WeatherModel(
        "Victoria",
        1671741336000,
        -5.0f,
        "Clear",
        "03d",
    )

    @Test
    fun weatherItemUI() {
        composeTestRule.setContent {
            WeatherItem(weatherModel = model)
        }


        composeTestRule
            .onNodeWithText("Victoria")
            .assertExists("City Label Not Populated")

        composeTestRule
            .onNodeWithText("Clear")
            .assertExists("Weather Label Not Populated")

        composeTestRule
            .onNodeWithText(timeString)
            .assertExists("Time Label Not Populated")

        composeTestRule
            .onNodeWithText("$temperature")
            .assertExists("Time Label Not Populated")
    }
}