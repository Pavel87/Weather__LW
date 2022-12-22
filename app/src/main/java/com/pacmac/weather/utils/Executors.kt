package com.pacmac.weather.utils

import io.reactivex.rxjava3.core.Scheduler

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-21
 */
interface Executors {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}