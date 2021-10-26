package com.example.waetherapp.data.weather

import com.example.waetherapp.data.entity.FavouriteForecast
import com.example.waetherapp.data.entity.Forecast
import com.example.waetherapp.data.entity.Location
import kotlinx.coroutines.flow.Flow
import com.example.waetherapp.utils.Result

interface WeatherRepository {
    suspend fun getForecastById(id: Int): Result<Forecast>

    suspend fun getForecastByLocation(location: Location): Result<Forecast>

    suspend fun getUpdatedForecast(forecast: Forecast): Result<Forecast>

    suspend fun getObservableCurrentForecast(): Flow<Result<Forecast>>

    suspend fun getFavouriteForecasts(): Result<List<FavouriteForecast>>

    suspend fun updateFavouriteForecasts()

    suspend fun starEvent(forecast: Forecast, isStarred: Boolean)

    suspend fun isEmpty(): Boolean
}