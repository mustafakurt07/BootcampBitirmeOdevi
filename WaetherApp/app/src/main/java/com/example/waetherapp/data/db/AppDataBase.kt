package com.example.waetherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.waetherapp.data.entity.CurrentForecast
import com.example.waetherapp.data.entity.Forecast

@Database(entities = [CurrentForecast::class, Forecast::class], version = 5)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getForecastDao(): ForecastDao

    companion object {
        const val DATABASE_NAME = "weather-db"
    }
}