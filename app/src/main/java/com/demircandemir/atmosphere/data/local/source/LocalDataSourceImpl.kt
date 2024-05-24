package com.demircandemir.atmosphere.data.local.source

import com.demircandemir.atmosphere.data.local.CityDatabase
import com.demircandemir.atmosphere.data.local.CityEntity
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity

class LocalDataSourceImpl(
    cityDatabase: CityDatabase
): LocalDataSource {

    private val cityDao = cityDatabase.cityDao()
    override suspend fun getAllCities() = cityDao.getAllCities()

    override suspend fun findCityByName(cityName: String) = cityDao.findCityByName(cityName)

    override suspend fun insertCity(city: CityEntity) = cityDao.insertCity(city)
    override suspend fun insertCities(cities: List<CityEntity>)  = cityDao.insertCities(cities)

    override suspend fun insertWeatherData(weatherData: WeatherDataEntity) = cityDao.insertWeatherData(weatherData)

    override fun getAllWeatherData() = cityDao.getAllWeatherData()


}