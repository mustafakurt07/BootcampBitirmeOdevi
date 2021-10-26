package com.example.waetherapp.utils

import androidx.appcompat.app.AppCompatDelegate
import com.example.waetherapp.utils.Theme.LIGHT
import com.example.waetherapp.utils.Theme.NIGHT

object ThemeUtils {
    fun setAppTheme(theme: Theme) {
        when (theme) {
            LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}