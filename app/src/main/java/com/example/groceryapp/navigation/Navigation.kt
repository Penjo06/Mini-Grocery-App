package com.example.groceryapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.groceryapp.screens.auth.login.LoginScreen
import com.example.groceryapp.screens.cart.CartScreen
import com.example.groceryapp.screens.checkout.CheckoutScreen
import com.example.groceryapp.screens.home.HomeScreen
import com.example.groceryapp.screens.success.SuccessScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GroceryNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LoginScreen.name) {
        composable(Screens.LoginScreen.name){
            LoginScreen(navController)
        }
        composable(Screens.HomeScreen.name){
            HomeScreen(navController)
        }
        composable(Screens.CartScreen.name){
            CartScreen(navController)
        }
        composable(Screens.CheckoutScreen.name){
            CheckoutScreen(navController)
        }

        val route = Screens.SuccessScreen.name
        composable (
            "$route/{orderId}/{estimatedDelivery}/{formattedPrice}",
            arguments = listOf(
                navArgument("orderId"){ type = NavType.StringType },
                navArgument("estimatedDelivery"){ type = NavType.StringType },
                navArgument("formattedPrice"){ type = NavType.StringType }
            )
        ){backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")?: ""
            val estimatedDelivery = backStackEntry.arguments?.getString("estimatedDelivery")?: ""
            val formattedPrice = backStackEntry.arguments?.getString("formattedPrice")?: ""

            SuccessScreen(navController, orderId, estimatedDelivery, formattedPrice)

        }
    }
}