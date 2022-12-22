package com.pacmac.weather.data

import com.google.gson.annotations.SerializedName

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-21
 */
data class WeatherResponse(
    @SerializedName("name")
    val city: String,
    @SerializedName("dt")
    val time: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("main")
    val temperature: Temperature,
    @SerializedName("wind")
    val wind: Wind,
)

data class Weather(
    @SerializedName("main") val weather: String = "",
    @SerializedName("icon") val icon: String = "",
)

data class Temperature(
    @SerializedName("temp") val temperature: Float,
)

data class Wind(
    @SerializedName("speed") val speed: Float,
    @SerializedName("degree") val degree: Int
)


//{
//  "coord": {
//    "lon": -0.1257,
//    "lat": 51.5085
//  },
//  "weather": [
//    {
//      "id": 803,
//      "main": "Clouds",
//      "description": "broken clouds",
//      "icon": "04n"
//    }
//  ],
//  "base": "stations",
//  "main": {
//    "temp": 279.78,
//    "feels_like": 277.55,
//    "temp_min": 278.22,
//    "temp_max": 280.52,
//    "pressure": 1004,
//    "humidity": 95
//  },
//  "visibility": 9000,
//  "wind": {
//    "speed": 3.09,
//    "deg": 230
//  },
//  "clouds": {
//    "all": 75
//  },
//  "dt": 1671687652,
//  "sys": {
//    "type": 2,
//    "id": 2075535,
//    "country": "GB",
//    "sunrise": 1671696256,
//    "sunset": 1671724421
//  },
//  "timezone": 0,
//  "id": 2643743,
//  "name": "London",
//  "cod": 200
//}