package com.demircandemir.atmosphere.data.remote.source

import com.demircandemir.atmosphere.domain.model.WeatherResponse
import com.demircandemir.atmosphere.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getWeatherForecast(lat: Double, lon: Double): Flow<ApiResult<WeatherResponse>>


    fun getWeatherForecast2(lat: Double, lon: Double): Flow<WeatherResponse>
}