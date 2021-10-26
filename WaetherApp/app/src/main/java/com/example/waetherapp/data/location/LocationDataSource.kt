package com.example.waetherapp.data.location

import com.example.waetherapp.base.NetworkDataSource
import com.example.waetherapp.data.entity.Location
import com.example.waetherapp.utils.Result

interface LocationDataSource : NetworkDataSource {
    suspend fun request(locationName: String): Result<List<Location>>
}
