package com.demircandemir.atmosphere.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_speed: Double
) {
    private val zonedDateTime: ZonedDateTime
        @RequiresApi(Build.VERSION_CODES.O)
        get() = convertUnixTimestampToZonedDateTime(dt.toLong())

    val year: Int
        @RequiresApi(Build.VERSION_CODES.O)
        get() = zonedDateTime.year

    val month: Int
        @RequiresApi(Build.VERSION_CODES.O)
        get() = zonedDateTime.monthValue

    val day: Int
        @RequiresApi(Build.VERSION_CODES.O)
        get() = zonedDateTime.dayOfMonth

    val hour: Int
        @RequiresApi(Build.VERSION_CODES.O)
        get() = zonedDateTime.hour

    val minute: Int
        @RequiresApi(Build.VERSION_CODES.O)
        get() = zonedDateTime.minute
}