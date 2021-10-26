package com.example.waetherapp.data.location

import com.example.waetherapp.data.entity.Location
import com.example.waetherapp.utils.Result

interface LocationRepository {
    /**
     * Performs GET request to the OpenCage API to fetch locations
     */
    suspend fun getLocationsByName(locationName: String): Result<List<Location>>
}