package com.demircandemir.atmosphere.domain.use_cases.get_searched_cities

import com.demircandemir.atmosphere.data.WeatherRepository
import javax.inject.Inject

class GetSearchedCitiesUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String) = repository.findCityByName(cityName)
}