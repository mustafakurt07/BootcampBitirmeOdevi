package com.example.waetherapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.waetherapp.utils.appDatabase
import com.example.waetherapp.utils.mainViewModel
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModel  {
    private val viewModel = mainViewModel
    private val database = appDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @After
    fun close() {
        database.close()
    }

    @Test
    fun testDefaultValues() {
        // TODO: Bad test
//        assertTrue(getValue(viewModel.isAppFirstLaunched))
//        assertEquals(DataNotFound, getValue(viewModel.forecastFailure))
//        assertFalse(getValue(viewModel.isLoading))
//        assertNull(getValue(viewModel.forecast))
    }
}