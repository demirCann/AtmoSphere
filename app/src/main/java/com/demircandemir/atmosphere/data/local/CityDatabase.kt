package com.demircandemir.atmosphere.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity
import com.demircandemir.atmosphere.utils.Converters

@Database(entities = [CityEntity::class, WeatherDataEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
