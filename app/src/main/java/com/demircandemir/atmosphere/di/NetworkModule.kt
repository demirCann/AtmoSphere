package com.demircandemir.atmosphere.di

import com.demircandemir.atmosphere.data.remote.source.RemoteDataSource
import com.demircandemir.atmosphere.data.remote.source.RemoteDataSourceImpl
import com.demircandemir.atmosphere.data.remote.source.WeatherApiService
import com.demircandemir.atmosphere.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


val json = Json {
    ignoreUnknownKeys = true
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(weatherApi: WeatherApiService): RemoteDataSource {
        return RemoteDataSourceImpl(weatherApi)
    }




}