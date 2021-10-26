package com.example.waetherapp.data.weather


import com.example.waetherapp.base.NetworkDataSource
import com.example.waetherapp.data.entity.Forecast
import com.example.waetherapp.data.entity.Location
import com.example.waetherapp.utils.Result
import java.util.*

interface WeatherDataSource : NetworkDataSource {
    /**
     * Performs GET request to the OpenWeatherMap API to fetch new [Forecast]
     */
    suspend fun request(location: Location, locale: Locale): Result<Forecast>
}