package com.demircandemir.atmosphere.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    onNavigateCityListScreen: () -> Unit,
    selectedCityIndex: Int,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val weatherDataEntities by homeViewModel.weatherDataEntities.collectAsState()

    if (weatherDataEntities.isNotEmpty()) {
        HorizontalPage(
            weatherDataEntities = weatherDataEntities,
            selectedCityIndex,
            onNavigateCityListScreen = onNavigateCityListScreen
        )
    } else {
        // Show loading or empty state

    }


}