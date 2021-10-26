package com.example.waetherapp.di

import com.example.waetherapp.data.entity.FavouriteForecast
import com.example.waetherapp.data.entity.Location
import com.example.waetherapp.ui.kurt.favourite.FavouriteAdapter
import com.example.waetherapp.ui.kurt.search.SearchAdapter
import com.example.waetherapp.ui.kurt.weather.DailyForecastAdapter
import com.example.waetherapp.ui.kurt.weather.HourlyForecastAdapter
import org.koin.dsl.module

val uiModule = module {
    factory { HourlyForecastAdapter() }
    factory { DailyForecastAdapter() }
    factory { (onLocationChange: (Location) -> Unit) -> SearchAdapter(onLocationChange) }
    factory { (onForecastChange: (FavouriteForecast) -> Unit) -> FavouriteAdapter(onForecastChange) }
}