package com.demircandemir.atmosphere.di

import com.demircandemir.atmosphere.data.WeatherRepository
import com.demircandemir.atmosphere.domain.use_cases.UseCases
import com.demircandemir.atmosphere.domain.use_cases.get_searched_cities.GetSearchedCitiesUseCase
import com.demircandemir.atmosphere.domain.use_cases.get_weather_forecast.GetWeatherForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUseCases(weatherRepository: WeatherRepository): UseCases {
        return UseCases(
            getWeatherForecast = GetWeatherForecastUseCase(weatherRepository),
            getSearchedCities = GetSearchedCitiesUseCase(weatherRepository)
        )
    }

}