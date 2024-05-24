package com.demircandemir.atmosphere.data.local.source

import com.demircandemir.atmosphere.data.local.CityEntity
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getAllCities(): List<CityEntity>

    suspend fun findCityByName(cityName: String): List<CityEntity?>

    suspend fun insertCity(city: CityEntity)

    suspend fun insertCities(cities: List<CityEntity>)

    suspend fun insertWeatherData(weatherData: WeatherDataEntity)

    fun getAllWeatherData(): Flow<List<WeatherDataEntity>>
}