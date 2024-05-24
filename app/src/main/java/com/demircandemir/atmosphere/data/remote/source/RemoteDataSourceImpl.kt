package com.demircandemir.atmosphere.data.remote.source

import com.demircandemir.atmosphere.domain.model.WeatherResponse
import com.demircandemir.atmosphere.utils.ApiResult
import com.demircandemir.atmosphere.utils.Constants.API_KEY
import com.demircandemir.atmosphere.utils.apiFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(
    private val weatherApi: WeatherApiService
): RemoteDataSource {
    override fun getWeatherForecast(lat: Double, lon: Double): Flow<ApiResult<WeatherResponse>> =
        apiFlow { weatherApi.getWeather(lat, lon, appid =API_KEY) }

    override fun getWeatherForecast2(lat: Double, lon: Double): Flow<WeatherResponse> {
        return flow {
            weatherApi.getWeather2(lat, lon, appid = API_KEY)
        }
    }
}