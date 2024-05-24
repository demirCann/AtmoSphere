package com.demircandemir.atmosphere.presentation.add_city

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchCityScreen(
    onCloseClicked: () -> Unit,
    onClickedCity: () -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val searchedMeals by searchViewModel.searchedMeals.collectAsState()
    val weatherState by searchViewModel.weatherState.collectAsState()

    Log.d("SearchScreen", "searchedMeals: $searchedMeals")

    Scaffold(
        topBar = {
            SearchTopAppBar(
                text = searchQuery,
                onTextChange = { searchViewModel.updateSearchQuery(it) },
                onClosedClicked = { onCloseClicked() },
                onSearchClicked = { searchViewModel.searchMeals(it) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                SearchedListContent(
                    searchedCities = searchedMeals,
                    onFetchDataFromRemote = { lat, lon ->
                        searchViewModel.getWeatherForecast(lat, lon)
                        when (val state = weatherState) {
                            is WeatherState2.Loading -> {
                                // Loading
                                Log.d("SearchScreen", "WeatherState.Loading")
//                        CircularProgressIndicator()
                            }
                            is WeatherState2.Success -> {
                                // Success
                                Log.d("SearchScreen", "WeatherState.Success")
                                val data = state.data

                                    searchViewModel.insertWeatherData(
                                        WeatherDataEntity(
                                            cityName = data.timezone,
                                            latitude = data.lat,
                                            longitude = data.lon,
                                            temperature = data.current.temp,
                                            dt = data.current.hour,
                                            feelsLike = data.current.feels_like,
                                            pressure = data.current.pressure,
                                            humidity = data.current.humidity,
                                            weatherMain = data.current.weather[0].main,
                                            weatherDescription = data.current.weather[0].description,
                                            windSpeed = data.current.wind_speed,
                                            windDeg = data.current.wind_deg,
                                            hourly = data.hourly,
                                            daily = data.daily
                                        )
                                    )

                            }
                            is WeatherState2.Error -> {
                                // Error
                                val message = state.message
                                Log.d("SearchScreen", "WeatherState.Error - $message")

                            }
                            is WeatherState2.Idle -> {
                                // Initial state or no operation
                            }
                        }

                        onClickedCity()


                    },
                    onClickedCity = { onClickedCity() },
                    paddingValues = paddingValues
                )

                when (val state = weatherState) {
                    is WeatherState2.Loading -> {
                        // Loading
                        Log.d("SearchScreen", "WeatherState.Loading")
//                        CircularProgressIndicator()
                    }
                    is WeatherState2.Success -> {
                        // Success
                        Log.d("SearchScreen", "WeatherState.Success")
                        val data = state.data
                        LaunchedEffect(data) {
                            searchViewModel.insertWeatherData(
                                WeatherDataEntity(
                                    cityName = data.timezone,
                                    latitude = data.lat,
                                    longitude = data.lon,
                                    temperature = data.current.temp,
                                    dt = data.current.hour,
                                    feelsLike = data.current.feels_like,
                                    pressure = data.current.pressure,
                                    humidity = data.current.humidity,
                                    weatherMain = data.current.weather[0].main,
                                    weatherDescription = data.current.weather[0].description,
                                    windSpeed = data.current.wind_speed,
                                    windDeg = data.current.wind_deg,
                                    hourly = data.hourly,
                                    daily = data.daily
                                )
                            )
                        }
                    }
                    is WeatherState2.Error -> {
                        // Error
                        val message = state.message
                        Log.d("SearchScreen", "WeatherState.Error - $message")
                        Text("Error: $message")
                    }
                    is WeatherState2.Idle -> {
                        // Initial state or no operation
                    }
                }
            }
        }
    )
}
