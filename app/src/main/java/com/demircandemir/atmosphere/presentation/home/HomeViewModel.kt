package com.demircandemir.atmosphere.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.atmosphere.data.WeatherRepository
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _weatherDataEntities = MutableStateFlow<List<WeatherDataEntity>>(emptyList())
    val weatherDataEntities: StateFlow<List<WeatherDataEntity>> = _weatherDataEntities

    init {
        fetchWeatherDataEntities()
    }

    private fun fetchWeatherDataEntities() {
        viewModelScope.launch {
            repository.getAllWeatherData().collect {
                _weatherDataEntities.value = it
            }
        }
    }


}