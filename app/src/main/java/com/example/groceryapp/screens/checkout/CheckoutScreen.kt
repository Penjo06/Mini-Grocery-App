package com.example.groceryapp.screens.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.groceryapp.navigation.Screens
import com.example.groceryapp.screens.components.GroceryAppBar
import com.example.groceryapp.screens.components.PaymentOptionRow
import com.example.groceryapp.viewmodel.CartViewModel

@Composable
fun CheckoutScreen(navController: NavController, viewModel: CartViewModel = hiltViewModel()){

    val cartItems by viewModel.cartItems.collectAsState()

    val totalPrice = cartItems.sumOf {
        it.price * it.quantity
    }
    val formattedPrice = "%.2f".format(totalPrice)

    var address by remember {
        mutableStateOf("")
    }

    var selectedPayment by remember {
        mutableStateOf("")
    }

    Scaffold (topBar = {
        GroceryAppBar(title = "Checkout",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            navController = navController,
            showCart = false
            ){
            navController.popBackStack()
        }
    }){ padding->
        Column (modifier = Modifier.padding(padding)
            .padding(6.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Top){

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Delivery Address")
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Surface (modifier = Modifier.padding(6.dp)
                .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.onPrimary
            ){
                Column {
                    Text("Select Payment Method",
                        modifier = Modifier.padding(4.dp),
                        color = MaterialTheme.colorScheme.onBackground)

                    Spacer(modifier = Modifier.height(8.dp))

                    PaymentOptionRow(selectedPayment){
                        selectedPayment = it
                    }

                }
            }
            HorizontalDivider()

            Spacer(modifier = Modifier.height(16.dp))
            Text("Total: Rs.$formattedPrice",
                style = MaterialTheme.typography.titleLarge)

            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom){

                val orderId = "ORD-" + System.currentTimeMillis()
                    .toString()
                    .takeLast(5)

                val estimatedDelivery = "30-40 mins"

                Button(onClick = {
                    viewModel.clearCart()
                    navController.navigate("${Screens.SuccessScreen.name}/$orderId/$estimatedDelivery/$formattedPrice")
                },
                    modifier = Modifier.fillMaxWidth()) {

                    Text("Place Order")
                }
            }
        }
    }

}

