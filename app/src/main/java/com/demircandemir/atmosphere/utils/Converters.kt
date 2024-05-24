package com.demircandemir.atmosphere.utils

import androidx.room.TypeConverter
import com.demircandemir.atmosphere.domain.model.Daily
import com.demircandemir.atmosphere.domain.model.Hourly
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromHourlyList(hourly: List<Hourly>): String {
        val type = object : TypeToken<List<Hourly>>() {}.type
        return Gson().toJson(hourly, type)
    }

    @TypeConverter
    fun toHourlyList(hourlyString: String): List<Hourly> {
        val type = object : TypeToken<List<Hourly>>() {}.type
        return Gson().fromJson(hourlyString, type)
    }

    @TypeConverter
    fun fromDailyList(daily: List<Daily>): String {
        val type = object : TypeToken<List<Daily>>() {}.type
        return Gson().toJson(daily, type)
    }

    @TypeConverter
    fun toDailyList(dailyString: String): List<Daily> {
        val type = object : TypeToken<List<Daily>>() {}.type
        return Gson().fromJson(dailyString, type)
    }
}
