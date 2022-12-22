package com.pacmac.weather.data

import com.pacmac.weather.Constants
import com.pacmac.weather.WeatherSearchUI.WeatherViewModel
import com.pacmac.weather.utils.Executors
import com.pacmac.weather.utils.ExecutorsImpl
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-21
 */

val dataModule = module {
    single<WeatherService> { retrofit.create(WeatherService::class.java) }
    factory<WeatherRepository> { WeatherRepositoryImpl(service = get()) }
}

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val utilModule = module {
    single<Executors> { ExecutorsImpl() }
}

val viewModels = module {
    viewModel { WeatherViewModel(get(), get()) }
}