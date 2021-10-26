package com.example.waetherapp.ui.kurt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.example.waetherapp.R
import com.example.waetherapp.base.BaseFragment
import com.example.waetherapp.ui.kurt.search.SearchFragment
import com.example.waetherapp.ui.kurt.weather.WeatherFragment
import com.example.waetherapp.utils.extension.observe
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            observe(viewModel.isAppFirstLaunched, ::showNextScreen)
    }

    /**
     * If app is launched for the first time, then navigates to [SearchFragment] to pick the location.
     *
     * If not - opens [WeatherFragment].
     */
    private fun showNextScreen(isAppFirstLaunched: Boolean) {
        val fragment: BaseFragment = if (isAppFirstLaunched) {
            SearchFragment.newInstance()
        } else {
            WeatherFragment.newInstance()
        }
        supportFragmentManager.commitNow {
            replace(R.id.mainContainer, fragment)
        }
    }
}