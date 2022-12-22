package com.pacmac.weather

import com.pacmac.weather.WeatherSearchUI.WeatherViewModel
import com.pacmac.weather.data.*
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


/**
 * @Author: Pavel Machala
 * @Date: 2022-12-22
 */
internal class WeatherViewModelTest {


    private var repository: WeatherRepository = mock(WeatherRepository::class.java)

    private val time = System.currentTimeMillis() / 1000

    @Test
    fun searchWeather() {
        val viewModel = WeatherViewModel(repository, TestExecutors())
        val city = "Vancouver"
        `when`(repository.getWeatherForCity(city)).thenReturn(
            Single.just(
                WeatherResponse(
                    city = city,
                    time = time.toInt(),
                    weather = listOf(Weather("Snow", "bba0")),
                    temperature = Temperature(temperature = -5f),
                    wind = Wind(1.0f, 200)
                )
            )
        )

        viewModel.searchWeather(city)

        Assert.assertEquals(1, viewModel.weatherList.size)
        Assert.assertEquals(city, viewModel.weatherList[0].city)
    }

    @Test
    fun searchWeather_Error() {
        val viewModel = WeatherViewModel(repository, TestExecutors())
        val city = "Vancouver"

        `when`(repository.getWeatherForCity(city)).thenReturn(
            Single.error(Exception("Test Exception"))
        )

        viewModel.searchWeather(city)

        Assert.assertEquals(0, viewModel.weatherList.size)
        Assert.assertTrue(viewModel.onError.value)
    }

    @Test
    fun searchWeatherEmptyCity() {
        val viewModel = WeatherViewModel(repository, TestExecutors())
        viewModel.searchWeather("")
        Assert.assertTrue(viewModel.onError.value)
    }
}