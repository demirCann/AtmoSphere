package com.demircandemir.atmosphere.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<CityEntity>

    @Query("SELECT * FROM cities WHERE name LIKE :cityName")
    suspend fun findCityByName(cityName: String): List<CityEntity?>

    @Insert
    suspend fun insertCity(cityName: CityEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertCities(cities: List<CityEntity>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherData: WeatherDataEntity)

    @Query("SELECT * FROM weather_data")
    fun getAllWeatherData(): Flow<List<WeatherDataEntity>>
}
