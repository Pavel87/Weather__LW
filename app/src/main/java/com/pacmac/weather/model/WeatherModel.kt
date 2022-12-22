package com.pacmac.weather.model

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-21
 */
data class WeatherModel(
    val city: String,
    val time: Long,
    val temperature: Float,
    val weather: String,
    val icon: String,
)


