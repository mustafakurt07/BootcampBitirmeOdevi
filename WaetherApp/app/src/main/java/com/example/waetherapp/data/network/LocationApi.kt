package com.example.waetherapp.data.network

import com.example.waetherapp.data.network.json.opencage.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("/geocode/v1/json")
    suspend fun getLocationsByNameAsync(
        @Query("q", encoded = true) locationName: String,
        @Query("key") apiKey: String
    ): Response<LocationResponse>
}