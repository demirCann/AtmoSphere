package com.demircandemir.atmosphere.di

import android.content.Context
import androidx.room.Room
import com.demircandemir.atmosphere.data.local.CityDatabase
import com.demircandemir.atmosphere.data.local.source.LocalDataSource
import com.demircandemir.atmosphere.data.local.source.LocalDataSourceImpl
import com.demircandemir.atmosphere.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CityDatabase {
        return Room.databaseBuilder(
            context,
            CityDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideLocalDataSource(
        cityDatabase: CityDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(cityDatabase = cityDatabase)
    }



}