package com.demircandemir.atmosphere.presentation.destination_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.atmosphere.data.WeatherRepository
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _allWeatherData = MutableStateFlow<List<WeatherDataEntity>>(emptyList())
    val allWeatherData: StateFlow<List<WeatherDataEntity>> = _allWeatherData

    fun getDestinations() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllWeatherData().collect {
                _allWeatherData.value = it
            }
        }
    }

}