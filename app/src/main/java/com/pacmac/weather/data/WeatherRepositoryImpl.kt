package com.pacmac.weather.data

import io.reactivex.rxjava3.core.Single

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-21
 */
class WeatherRepositoryImpl(private val service: WeatherService): WeatherRepository {

    override fun getWeatherForCity(cityName: String): Single<WeatherResponse> {
        return service.getWeatherByCity(cityName)
    }
}