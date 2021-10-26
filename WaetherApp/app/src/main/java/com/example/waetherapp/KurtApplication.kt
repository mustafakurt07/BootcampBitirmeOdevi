package com.example.waetherapp

import android.app.Application
import com.example.waetherapp.data.AppSettings
import com.example.waetherapp.di.appModule
import com.example.waetherapp.di.networkModule
import com.example.waetherapp.di.uiModule
import com.example.waetherapp.utils.ServiceConnector
import com.example.waetherapp.utils.ThemeUtils
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication.Companion.init
import org.koin.core.context.startKoin

class KurtApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Set Up Koin DI
        startKoin {
            androidContext(this@KurtApplication)
            modules(appModule, networkModule, uiModule)
        }

        // Set Up App Theme
        val settings by inject<AppSettings>()
        ThemeUtils.setAppTheme(settings.theme)
    }

}