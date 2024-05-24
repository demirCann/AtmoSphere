package com.demircandemir.atmosphere.data

import com.demircandemir.atmosphere.data.local.CityEntity
import com.demircandemir.atmosphere.data.local.source.LocalDataSource
import com.demircandemir.atmosphere.data.remote.source.RemoteDataSource
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {
    fun getWeatherForecast(lat: Double, lon: Double) = remote.getWeatherForecast(lat, lon)

    suspend fun findCityByName(cityName: String) = local.findCityByName(cityName)


    suspend fun insertCities(cities: List<CityEntity>) = local.insertCities(cities)

    suspend fun getAllCities() = local.getAllCities()

    suspend fun insertWeatherData(weatherData: WeatherDataEntity) = local.insertWeatherData(weatherData)

    fun getAllWeatherData() = local.getAllWeatherData()





}