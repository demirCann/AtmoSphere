package com.demircandemir.atmosphere.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale

@Serializable
data class Daily(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: FeelsLike,
    val humidity: Int,
    val moon_phase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double? = null,
    val summary: String,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
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

    val dayOfWeek: String
        @RequiresApi(Build.VERSION_CODES.O)
        get() = zonedDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
}


@RequiresApi(Build.VERSION_CODES.O)
fun convertUnixTimestampToZonedDateTime(timestamp: Long): ZonedDateTime {
    return Instant.ofEpochSecond(timestamp).atZone(ZoneId.systemDefault())
}