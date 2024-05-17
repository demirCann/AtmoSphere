package com.demircandemir.atmosphere.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash_screen_route")

    data object Weather: Screens("list_screen_route")

    data object DestinationsList: Screens("destinations_list_screen_route")
}