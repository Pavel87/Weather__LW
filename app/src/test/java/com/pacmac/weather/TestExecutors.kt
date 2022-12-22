package com.pacmac.weather

import com.pacmac.weather.utils.Executors
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.TrampolineScheduler

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-22
 */
class TestExecutors: Executors {
    override fun io(): Scheduler {
        return TrampolineScheduler.instance()
    }

    override fun ui(): Scheduler {
        return TrampolineScheduler.instance()
    }

    override fun computation(): Scheduler {
        return TrampolineScheduler.instance()
    }
}