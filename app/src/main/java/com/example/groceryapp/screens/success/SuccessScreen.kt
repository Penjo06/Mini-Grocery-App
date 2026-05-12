package com.example.groceryapp.screens.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.groceryapp.navigation.Screens

@Composable
fun SuccessScreen(navController: NavController, orderId: String, estimatedDelivery: String, formattedPrice: String){
    Column (modifier = Modifier.fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text("🎉Order Placed Successfully🎉",
            style = MaterialTheme.typography.headlineSmall)

        Card (colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
            elevation = CardDefaults.cardElevation(4.dp)){

            Column (modifier = Modifier.padding(8.dp)){
                Text("Order Summary..",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(9.dp))

                Text("Order Id: $orderId",
                   color = MaterialTheme.colorScheme.onBackground)

                Spacer(modifier = Modifier.height(6.dp))
                Text("Estimated Delivery: $estimatedDelivery",
                    color = MaterialTheme.colorScheme.onBackground)

                Spacer(modifier = Modifier.height(6.dp))
                Text("Total Price: Rs.$formattedPrice",
                    color = MaterialTheme.colorScheme.onBackground)

            }
        }

        Text("Thank you for shopping with us!",
            style = MaterialTheme.typography.bodyMedium)


        Button(onClick = {
            navController.navigate(Screens.HomeScreen.name)
        }) {
            Text("Continue Shopping")
        }
    }
}