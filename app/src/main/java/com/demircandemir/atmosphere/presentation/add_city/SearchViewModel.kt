package com.demircandemir.atmosphere.presentation.add_city

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.atmosphere.data.WeatherRepository
import com.demircandemir.atmosphere.data.local.CityEntity
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity
import com.demircandemir.atmosphere.domain.model.WeatherResponse
import com.demircandemir.atmosphere.domain.use_cases.UseCases
import com.demircandemir.atmosphere.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class WeatherState2 {
    data object Loading : WeatherState2()
    data class Success(val data: WeatherResponse) : WeatherState2()
    data class Error(val message: String?) : WeatherState2()

    data object Idle : WeatherState2() {

    }
}

@HiltViewModel
class SearchViewModel @Inject constructor(
     private val useCases: UseCases,
     private val repository: WeatherRepository
) : ViewModel() {

     private val _searchQuery = mutableStateOf("")
     val searchQuery = _searchQuery


     private val _searchedMeals = MutableStateFlow<List<CityEntity?>>(emptyList())
     val searchedMeals = _searchedMeals.asStateFlow()

    private val _weatherState = MutableStateFlow<WeatherState2>(WeatherState2.Loading)
    val weatherState: StateFlow<WeatherState2> = _weatherState


     fun updateSearchQuery(newQuery: String) {
          _searchQuery.value = newQuery
     }


     fun searchMeals(query: String) {
          viewModelScope.launch(Dispatchers.IO) {
               _searchedMeals.value = useCases.getSearchedCities(cityName = query)
          }
     }


    fun getWeatherForecast(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getWeatherForecast(lat, lon).collect {
                when(it) {
                    is ApiResult.Success -> {
                        _weatherState.value = WeatherState2.Success(it.data!!)
                    }
                    is ApiResult.Error -> {
                        _weatherState.value = WeatherState2.Error(it.message ?: "An error occurred")
                    }
                    is ApiResult.Loading -> {
                        _weatherState.value = WeatherState2.Loading
                    }
                }
            }
        }
    }

    fun insertWeatherData(weatherData: WeatherDataEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWeatherData(weatherData)
        }
    }


}