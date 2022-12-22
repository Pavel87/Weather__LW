package com.pacmac.weather

import android.app.Application
import com.pacmac.weather.data.dataModule
import com.pacmac.weather.data.utilModule
import com.pacmac.weather.data.viewModels
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-21
 */
class WeatherApplication : Application() {


    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@WeatherApplication)
            modules(viewModels, dataModule, utilModule)
        }

        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }
    }
}