package com.demircandemir.atmosphere.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(Screens.Splash.route) {
//            SplashScreen(navController = navController)
        }
        composable(Screens.Weather.route) {
//            WeatherScreen(navController = navController)
        }
        composable(Screens.DestinationsList.route) {
//            DestinationsListScreen(navController = navController)
        }
    }
}