package com.demircandemir.atmosphere.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.demircandemir.atmosphere.utils.Converters

@Entity(tableName = "weather_data")
@TypeConverters(Converters::class)
data class WeatherDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val dt: Int,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val weatherMain: String,
    val weatherDescription: String,
    val windSpeed: Double,
    val windDeg: Int,
    val hourly: List<Hourly>,
    val daily: List<Daily>
)
