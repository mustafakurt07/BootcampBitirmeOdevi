package com.example.waetherapp.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class ServiceConnector {

    fun init() {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}