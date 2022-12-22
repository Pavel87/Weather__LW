package com.pacmac.weather.WeatherSearchUI

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.pacmac.weather.data.WeatherRepository
import com.pacmac.weather.model.WeatherModel
import com.pacmac.weather.utils.Executors
import io.reactivex.rxjava3.disposables.CompositeDisposable

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val executors: Executors
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val _weatherList = ArrayList<WeatherModel>().toMutableStateList()
    val weatherList: List<WeatherModel> = _weatherList

    val onError = mutableStateOf(false)

    private val _onLoading = mutableStateOf(false)
    val onLoading = _onLoading


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun searchWeather(city: String) {
        if (city.isEmpty()) {
            onError.value = true
            return
        }

        _onLoading.value = true

        val disposable = repository.getWeatherForCity(city)
            .subscribeOn(executors.io())
            .map {
                WeatherModel(
                    it.city,
                    it.time * 1000L,
                    it.temperature.temperature,
                    it.weather.firstOrNull()?.weather ?: "",
                    it.weather.firstOrNull()?.icon ?: "",
                )
            }
            .observeOn(executors.ui())
            .doOnSuccess { weather ->
                _onLoading.value = false
                _weatherList.add(weather)
            }
            .doOnError {
                _onLoading.value = false
                onError.value = true
            }
            .subscribe()

        compositeDisposable.add(disposable)
    }
}