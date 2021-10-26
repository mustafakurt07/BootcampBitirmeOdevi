package com.example.waetherapp.ui.kurt

import androidx.lifecycle.*
import com.example.waetherapp.data.AppSettings
import com.example.waetherapp.data.entity.FavouriteForecast
import com.example.waetherapp.data.entity.Forecast
import com.example.waetherapp.data.entity.Location
import com.example.waetherapp.data.weather.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherRepository: WeatherRepository,
    internal val settings: AppSettings
) : ViewModel() {

    /**
     * Holds displayed (current) forecast value
     */
    private val _forecast: MutableLiveData<Forecast> = MutableLiveData()
    val forecast: LiveData<Forecast>
        get() = _forecast

    /**
     * Holds failure which may occur when loading data
     */
    private val _forecastFailure: MutableLiveData<Exception> = MutableLiveData()
    val forecastFailure: LiveData<Exception>
        get() = _forecastFailure


    /**
     * Holds data loading status
     */
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    /**
     * Holds information about app launch
     */
    val isAppFirstLaunched: LiveData<Boolean> = liveData {
        emit(weatherRepository.isEmpty())
    }

    init {
        // Loading current forecast from database
        viewModelScope.launch {
            notifyLoadingStart()
            weatherRepository.getObservableCurrentForecast().collect { result ->
                result.fold(
                    onSuccess = ::handleFetchSuccess,
                    onFailure = ::handleFetchFailure
                )
            }
        }
        // Updating favourite locations forecasts
        viewModelScope.launch {
            weatherRepository.updateFavouriteForecasts()
        }
    }

    /**
     * Loads forecast by given [location]
     */
    fun fetchForecast(location: Location) {
        viewModelScope.launch {
            notifyLoadingStart()
            weatherRepository.getForecastByLocation(location).fold(
                onSuccess = ::handleFetchSuccess,
                onFailure = ::handleFetchFailure
            )
        }
    }

    /**
     * Loads forecast by given [favouriteForecast]
     */
    fun fetchForecast(favouriteForecast: FavouriteForecast) {
        viewModelScope.launch {
            notifyLoadingStart()
            weatherRepository.getForecastById(favouriteForecast.id).fold(
                onSuccess = ::handleFetchSuccess,
                onFailure = ::handleFetchFailure
            )
        }
    }

    /**
     * Updates given forecast (uses current forecast by default)
     */
    fun updateForecast(forecast: Forecast? = _forecast.value) = forecast?.let {
        viewModelScope.launch {
            notifyLoadingStart()
            weatherRepository.getUpdatedForecast(it).fold(
                onSuccess = ::handleFetchSuccess,
                onFailure = ::handleFetchFailure
            )
        }
    }

    /**
     * Changes favourite status of the current forecast
     */
    fun changeForecastFavouriteStatus(isFavourite: Boolean) {
        viewModelScope.launch {
            forecast.value?.let {
                it.isFavourite = isFavourite
                weatherRepository.starEvent(it, isFavourite)
            }
        }
    }

    /**
     * Applies new units from the [AppSettings] to the given forecast.
     *
     * Applies to the [_forecast] by default if parameter was not passed.
     */
    fun applyNewUnits(forecast: Forecast? = _forecast.value) {
        viewModelScope.launch(Dispatchers.Default) {
            _forecast.postValue(forecast?.apply {
                updateTemperatureUnit(settings.temperature)
                updateWindUnit(settings.wind)
                updatePressureUnit(settings.pressure)
            })
        }
    }


    /**
     * Default handler for success loading of the [forecast]
     */
    private fun handleFetchSuccess(forecast: Forecast) {
        applyNewUnits(forecast)
        _forecastFailure.value = null // removing old failure if it's not null
        notifyLoadingEnd()
    }

    /**
     * Default handler for failure occur when loading the [forecast]
     */
    private fun handleFetchFailure(failure: Exception) {
        _forecastFailure.value = failure
        notifyLoadingEnd()
    }

    /**
     * Changes status when loading of data starts
     */
    private fun notifyLoadingStart() {
        _isLoading.postValue(true)
    }


    /**
     * Changes status when loading of data ends
     */
    private fun notifyLoadingEnd() {
        _isLoading.postValue(false)
    }
}