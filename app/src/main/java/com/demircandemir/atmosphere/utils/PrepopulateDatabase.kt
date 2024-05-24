package com.demircandemir.atmosphere.utils

import com.demircandemir.atmosphere.data.WeatherRepository
import com.demircandemir.atmosphere.data.local.CityEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PrepopulateDatabase @Inject constructor (
    private val repository: WeatherRepository
) {

    fun prepopulateDatabase() {
        val popularCities = listOf(
            CityEntity(name = "New York", latitude = 40.7128, longitude = -74.0060),
            CityEntity(name = "Los Angeles", latitude = 34.0522, longitude = -118.2437),
            CityEntity(name = "Chicago", latitude = 41.8781, longitude = -87.6298),
            CityEntity(name = "Houston", latitude = 29.7604, longitude = -95.3698),
            CityEntity(name = "Phoenix", latitude = 33.4484, longitude = -112.0740)
        )

        CoroutineScope(Dispatchers.IO).launch {
            repository.insertCities(popularCities)
        }
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return repository.getAllCities().isEmpty()
    }


}
