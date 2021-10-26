package com.example.waetherapp.di

import com.example.waetherapp.data.AppSettings
import com.example.waetherapp.data.AppSettingsImpl
import com.example.waetherapp.data.db.DatabaseFactory
import com.example.waetherapp.data.location.LocationDataSource
import com.example.waetherapp.data.location.LocationRepository
import com.example.waetherapp.data.location.OpenCageDataSource
import com.example.waetherapp.data.location.OpenCageRepository
import com.example.waetherapp.data.weather.OpenWeatherMapDataSource
import com.example.waetherapp.data.weather.OpenWeatherMapRepository
import com.example.waetherapp.data.weather.WeatherDataSource
import com.example.waetherapp.data.weather.WeatherRepository
import com.example.waetherapp.ui.kurt.MainViewModel
import com.example.waetherapp.ui.kurt.favourite.FavouriteViewModel
import com.example.waetherapp.ui.kurt.search.SearchViewModel
import com.example.waetherapp.ui.kurt.settings.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    single<LocationDataSource> { OpenCageDataSource(get()) }
    single<LocationRepository> { OpenCageRepository(get()) }
    single<WeatherDataSource> { OpenWeatherMapDataSource(get()) }
    single<WeatherRepository> { OpenWeatherMapRepository(get(), get(), get()) }
    single<AppSettings> { AppSettingsImpl(androidApplication()) }
    single { DatabaseFactory.create(androidApplication()) }
}