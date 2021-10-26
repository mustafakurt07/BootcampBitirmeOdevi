package com.example.waetherapp.data.location

import com.example.waetherapp.data.entity.Location
import com.example.waetherapp.utils.Result


class OpenCageRepository(
    private val locationDataSource: LocationDataSource
): LocationRepository {
    override suspend fun getLocationsByName(locationName: String): Result<List<Location>> {
        return locationDataSource.request(locationName)
    }
}