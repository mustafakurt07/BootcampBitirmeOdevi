package com.example.waetherapp.ui.kurt.weather

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waetherapp.R
import com.example.waetherapp.base.BaseFragment
import com.example.waetherapp.data.entity.CurrentForecast
import com.example.waetherapp.data.entity.Forecast
import com.example.waetherapp.ui.kurt.MainActivity
import com.example.waetherapp.ui.kurt.MainViewModel
import com.example.waetherapp.ui.kurt.favourite.FavouriteFragment
import com.example.waetherapp.ui.kurt.search.SearchFragment
import com.example.waetherapp.utils.Pressure.HECTO_PASCALS
import com.example.waetherapp.utils.Pressure.MILLIMETERS_OF_MERCURY
import com.example.waetherapp.utils.Wind.KILOMETERS_PER_HOUR
import com.example.waetherapp.utils.Wind.METERS_PER_SECOND
import com.example.waetherapp.utils.exception.BadServerResponse
import com.example.waetherapp.utils.exception.NoConnection
import com.example.waetherapp.utils.extension.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.precipitation_card_view.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel


class WeatherFragment  : BaseFragment() {
    override val layoutId = R.layout.main_fragment
    private val viewModel: MainViewModel by sharedViewModel()
    private val hourlyForecastAdapter: HourlyForecastAdapter by inject()
    private val dailyForecastAdapter: DailyForecastAdapter by inject()

    companion object {
        fun newInstance() = WeatherFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            observe(forecast, ::renderForecast)
            observe(forecastFailure, ::handleFailure)
            observe(isLoading, ::onLoading)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        // Set up SwipeRefreshLayout
        swipeRefreshLayout.run {
            setProgressViewOffset(true, 0, 55.dp)
            setOnRefreshListener { viewModel.updateForecast() }
        }

        // Set up BottomAppBar
        (activity as MainActivity).setSupportActionBar(bottomAppBar)
        bottomAppBar.run {
            setNavigationOnClickListener {
                FavouriteFragment
                    .newInstance()
                    .show(parentFragmentManager, FavouriteFragment.TAG)
            }
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_search_action -> {
                        parentFragmentManager.commit {
                            replace(R.id.mainContainer, SearchFragment.newInstance())
                            addToBackStack(SearchFragment.TAG)
                        }
                        true
                    }
                    else -> false
                }
            }
        }

        // List configurations
        hourList.run {
            adapter = hourlyForecastAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
        }
        dayList.run {
            adapter = dailyForecastAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }

        // Add to favourite button (star)
        favouriteToggle.setOnClickListener {
            viewModel.changeForecastFavouriteStatus(favouriteToggle.isChecked)
        }
    }

    /**
     * Handles with [forecast] data
     */
    private fun renderForecast(forecast: Forecast) {
        renderCurrentForecast(forecast.currentForecast)
        hourlyForecastAdapter.submitList(forecast.hourForecastList)
        dailyForecastAdapter.submitList(forecast.dayForecastList)
        locationName.text = forecast.locationName
        favouriteToggle.isChecked = forecast.isFavourite
        if (mainContentLinearLayout.isInvisible) {
            mainContentLinearLayout.fadeIn()
        }
    }

    /**
     * Displays [currentForecast] data
     */
    private fun renderCurrentForecast(currentForecast: CurrentForecast) = with(currentForecast) {
        // Forecast description
        currentDescription.text = description
        currentDescriptionImage.animateScaleFadeChange(imageId)

        // Temperature
        currentTemperature.animateNumberChange(newValue = temperature.value, isSignShown = true)
        currentFeelsLike.animateNumberChange(newValue = feelsLike.value, isSignShown = true)

        // Humidity
        humidityCard.value.animateNumberChange(newValue = humidity)

        // Wind
        windCard.value.animateNumberChange(newValue = wind.value)
        when (currentForecast.wind.unit) {
            METERS_PER_SECOND ->
                windCard.unit.setText(R.string.wind_value_meters_per_second)
            KILOMETERS_PER_HOUR ->
                windCard.unit.setText(R.string.wind_value_kilometers_per_hour)
        }

        // Pressure
        pressureCard.value.animateNumberChange(newValue = pressure.value)
        when (currentForecast.pressure.unit) {
            MILLIMETERS_OF_MERCURY ->
                pressureCard.unit.setText(R.string.pressure_value_millimeters)
            HECTO_PASCALS ->
                pressureCard.unit.setText(R.string.pressure_value_pascals)
        }
    }

    /**
     * Handles with failures that may occur while loading data.
     *
     * Notifies user via SnackBar about them.
     */
    private fun handleFailure(failure: Exception?) {
        when (failure) {
            is NoConnection -> {
                snackbarAction(
                    coordinatorLayout,
                    bottomAppBar,
                    R.string.failure_connection_error,
                    R.string.button_try_again
                ) {
                    viewModel.updateForecast()
                }
            }
            is BadServerResponse -> {
                snackbarAction(
                    coordinatorLayout,
                    bottomAppBar,
                    R.string.failure_server_error,
                    R.string.button_try_again
                ) {
                    viewModel.updateForecast()
                }
            }
            else -> {
                // TODO: Handle other failures?
            }
        }
    }

    /**
     * Changes [swipeRefreshLayout] loading status when loading data
     */
    private fun onLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }
}