package com.demircandemir.atmosphere.data.remote.source

import com.demircandemir.atmosphere.domain.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/3.0/onecall")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String
    ): Response<WeatherResponse>


    @GET("data/3.0/onecall")
    suspend fun getWeather2(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String
    ): WeatherResponse

}