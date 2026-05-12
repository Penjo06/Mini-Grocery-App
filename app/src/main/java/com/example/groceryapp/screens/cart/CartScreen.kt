package com.example.groceryapp.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.groceryapp.data.local.entity.CartItemEntity
import com.example.groceryapp.navigation.Screens
import com.example.groceryapp.screens.components.GroceryAppBar
import com.example.groceryapp.viewmodel.CartViewModel

@Composable
fun CartScreen(navController: NavController, viewmodel: CartViewModel = hiltViewModel()){

    val cartItem by viewmodel.cartItems.collectAsState()

    val totalPrice = cartItem.sumOf { it.price * it.quantity }
    val formattedPrice = "%.2f".format(totalPrice)

    Scaffold (
        topBar = { GroceryAppBar(title = "My Cart",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            navController = navController,
            showCart = false,
            onArrowBackClicked = {
                navController.popBackStack()
            })
        }
    ){padding ->
        Column(modifier = Modifier.padding(padding)
            .padding(6.dp)
            .fillMaxSize()) {

            if(cartItem.isEmpty()){
                Column (modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Text("Your Cart is Empty...",
                        style = MaterialTheme.typography.displayLarge)
                }
            }else{
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top){

                    Surface(modifier = Modifier.padding(4.dp)
                        .fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        color = MaterialTheme.colorScheme.primary){

                        LazyColumn (modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp)){

                            items(cartItem){item ->
                                CartItemRow(item,
                                    onIncrease = {viewmodel.increaseQuantity(item)},
                                    onDecrease = {
                                        viewmodel.decreaseQuantity(item)
                                    })
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Column (modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom){

                        HorizontalDivider(modifier = Modifier.padding(bottom = 6.dp))

                        Text("Total Price: Rs.$formattedPrice",
                            style = MaterialTheme.typography.titleLarge)

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {navController.navigate(Screens.CheckoutScreen.name)},
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text("Proceed to Checkout")
                        }

                    }

                }
            }

        }
    }
}



@Composable
fun CartItemRow(item: CartItemEntity,
                onIncrease: ()-> Unit,
                onDecrease: ()-> Unit){

    Card (modifier = Modifier.fillMaxWidth()
        .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary)){


        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            AsyncImage(item.image, item.name,
                modifier = Modifier.size(100.dp)
                    .padding(3.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)){

                Text(item.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.height(4.dp))

                Text("Rs.${item.price}",
                    color = MaterialTheme.colorScheme.onBackground)

                Spacer(modifier = Modifier.height(4.dp))

               Row (verticalAlignment = Alignment.CenterVertically){

                   Button(onClick = {
                       onDecrease()
                   },
                       contentPadding = PaddingValues(
                           horizontal = 8.dp,
                           vertical = 4.dp
                       ),
                       modifier = Modifier.defaultMinSize(minWidth = 2.dp, minHeight = 1.dp)) {
                       Text("-")
                   }

                   Text("Quantity: ${item.quantity}",
                       color = MaterialTheme.colorScheme.onBackground)

                   Button(onClick = {
                       onIncrease()
                   },
                       contentPadding = PaddingValues(
                           horizontal = 8.dp,
                           vertical = 4.dp
                       ),
                       modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)) {
                       Text("+")
                   }
               }

            }
        }
    }
}