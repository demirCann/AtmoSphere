package com.demircandemir.atmosphere.domain.use_cases.get_weather_forecast

import com.demircandemir.atmosphere.data.WeatherRepository

class GetWeatherForecastUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(lat: Double, lon: Double) = weatherRepository.getWeatherForecast(lat, lon)
}
