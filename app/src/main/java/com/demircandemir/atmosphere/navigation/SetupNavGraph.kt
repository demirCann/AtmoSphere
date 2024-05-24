package com.demircandemir.atmosphere.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.demircandemir.atmosphere.presentation.add_city.SearchCityScreen
import com.demircandemir.atmosphere.presentation.destination_list.DestinationListScreen
import com.demircandemir.atmosphere.presentation.home.HomeScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.DestinationsList.route) {
        composable(Screens.Splash.route) {
//            SplashScreen(navController = navController)
        }
        composable("home_screen_route/{selectedCityIndex}") { backStackEntry ->
            val selectedCityIndex = backStackEntry.arguments?.getString("selectedCityIndex")?.toInt() ?: 0
            HomeScreen(
                onNavigateCityListScreen = {
                    navController.navigate(Screens.DestinationsList.route)
                },
                selectedCityIndex = selectedCityIndex
            )
        }
        composable(Screens.DestinationsList.route) {
                DestinationListScreen(
                    onSearchClicked = {
                        navController.navigate(Screens.AddDestinations.route)
                    },
                    onDestinationClicked = {
                        navController.navigate("home_screen_route/$it")
                    }
                )
        }

        composable(Screens.AddDestinations.route) {
            SearchCityScreen(
                onCloseClicked = { navController.popBackStack() },
                onClickedCity = { navController.navigate(Screens.DestinationsList.route) {
                    popUpTo(Screens.AddDestinations.route) { inclusive = true }
                } }
            )
        }
    }
}