package com.demircandemir.atmosphere.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash_screen_route")

    data object Home: Screens("home_screen_route")

    data object DestinationsList: Screens("destinations_list_screen_route")


    data object AddDestinations: Screens("add_destinations_screen_route")
}