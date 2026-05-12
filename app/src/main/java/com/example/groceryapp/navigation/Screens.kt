package com.example.groceryapp.navigation

enum class Screens {
    LoginScreen,
    HomeScreen,
    CartScreen,
    CheckoutScreen,
    SuccessScreen;

    companion object{
        fun fromRoute(route: String?): Screens
        = when(route?.substringBefore("/")){
            LoginScreen.name -> LoginScreen
            HomeScreen.name -> HomeScreen
            CartScreen.name -> CartScreen
            CheckoutScreen.name -> CheckoutScreen
            SuccessScreen.name -> SuccessScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}