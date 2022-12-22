package com.pacmac.weather.data

import com.pacmac.weather.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-21
 */
interface WeatherService {
    @GET(".")
    fun getWeatherByCity(
        @Query("q") city: String,
        @Query("units") units: String = Constants.UNITS_METRIC,
        @Query("appid") apiKey: String = Constants.API_KEY,
    ): Single<WeatherResponse>

}