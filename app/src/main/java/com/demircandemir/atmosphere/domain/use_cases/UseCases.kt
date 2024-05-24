package com.demircandemir.atmosphere.domain.use_cases

import com.demircandemir.atmosphere.domain.use_cases.get_searched_cities.GetSearchedCitiesUseCase
import com.demircandemir.atmosphere.domain.use_cases.get_weather_forecast.GetWeatherForecastUseCase

data class UseCases(
    val getWeatherForecast: GetWeatherForecastUseCase,
    val getSearchedCities: GetSearchedCitiesUseCase
)
