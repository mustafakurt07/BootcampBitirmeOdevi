package com.example.waetherapp.data.db

import android.content.Context
import androidx.room.Room

object DatabaseFactory {
    fun create(context: Context): AppDataBase {
        return Room
            .databaseBuilder(context, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .build()
    }
}