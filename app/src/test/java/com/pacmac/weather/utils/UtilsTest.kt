package com.pacmac.weather.utils

import org.junit.Assert
import org.junit.Test

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-22
 */
internal class UtilsTest {

    @Test
    fun `formatTime 0ms`() {
        Assert.assertEquals("", Utils.formatTime(0))
    }

    @Test
    fun `formatTime valid ts`() {
        val time = 1671741336000
        println(time)
        Assert.assertEquals("12-35-36", Utils.formatTime(time))
    }
}