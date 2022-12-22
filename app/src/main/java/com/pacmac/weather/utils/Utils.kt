package com.pacmac.weather.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-22
 */
object Utils {

    fun formatTime(timeMs: Long): String {
        if (timeMs == 0L) return ""
        val pattern = "HH-mm-ss"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(Date(timeMs))
    }
}