package com.example.waetherapp.utils.extension

import com.example.waetherapp.data.entity.FavouriteForecast
import com.example.waetherapp.data.entity.Forecast
import com.example.waetherapp.data.entity.Location


fun Forecast.toLocation() = Location(locationName, longitude, latitude)

fun Forecast.toFavouriteForecast() = FavouriteForecast(
    id = id,
    temperature = currentForecast.temperature,
    description = currentForecast.description,
    locationName = locationName,
    imageId = currentForecast.imageId
)