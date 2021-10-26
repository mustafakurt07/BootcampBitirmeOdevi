package com.example.waetherapp.ui.kurt.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waetherapp.data.entity.FavouriteForecast
import com.example.waetherapp.data.weather.WeatherRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(weatherRepository: WeatherRepository) : ViewModel() {

    private val _favouriteLocations: MutableLiveData<List<FavouriteForecast>> = MutableLiveData()
    val favouriteLocations: LiveData<List<FavouriteForecast>>
        get() = _favouriteLocations

    private val _failure: MutableLiveData<Exception> = MutableLiveData()
    val failure: LiveData<Exception>
        get() = _failure


    init {
        viewModelScope.launch {
            weatherRepository.getFavouriteForecasts().fold(
                onSuccess = { _favouriteLocations.value = it },
                onFailure = { _failure.value = it }
            )
        }
    }
}
