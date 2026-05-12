package com.example.groceryapp.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.groceryapp.data.model.Product
import com.example.groceryapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryAppBar(title: String,
                  icon: ImageVector? = null,
                  showCart: Boolean = true,
                  navController: NavController,
                  onArrowBackClicked: () -> Unit = {}) {
    TopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically){
            if(icon != null){
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Arrow Back",
                    modifier = Modifier.clickable { onArrowBackClicked.invoke() }
                )
                Spacer(modifier = Modifier.width(50.dp))
            }
            Text(text = title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                color = MaterialTheme.colorScheme.onBackground)
        }
    },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),

        actions = {
            if (showCart){
                Icon(imageVector = Icons.Filled.ShoppingCart, "Cart Icon",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigate(Screens.CartScreen.name)
                        },
                    tint = Color.Black)
            }
        }
    )
}


@Composable
fun ProductCard(product: Product,
                addOnClick: ()-> Unit) {
    Card (modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary)){
        Column (modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center){
            AsyncImage(model = product.image, "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = product.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Rs. ${product.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = addOnClick,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add to Cart")
            }
        }
    }
}


@Composable
fun CategoryItem(category: String,
                 isSelected: Boolean,
                 onClick: ()-> Unit) {
    Text(text = category,
        modifier = Modifier.padding(8.dp)
            .background(
                if(isSelected){
                    MaterialTheme.colorScheme.primary
                }else{
                    MaterialTheme.colorScheme.onPrimary
                },
                shape = RoundedCornerShape(20.dp)
            )
            .clickable{
                onClick()
            }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        color = if(isSelected)Color.Black else Color.White
    )
}


@Composable
fun SearchBarSection(searchQuery: String, onSearchChange: (String)-> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = {onSearchChange(it)},
        modifier = Modifier.fillMaxWidth()
            .padding(6.dp),
        label = {Text("Search Items",
            color = MaterialTheme.colorScheme.onBackground)},
        singleLine = true,
    )
}


@Preview
@Composable
fun PaymentOptionRow(selectedPayment: String = "",
                     onSelectionChange: (String)-> Unit={}) {

    Column (modifier = Modifier.fillMaxWidth()) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPayment == "Online Payment",
                onClick = {
                    onSelectionChange("Online Payment")
                }
            )
            Text(
                text = "Online Payment"
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPayment == "Cash on Delivery",
                onClick = {
                    onSelectionChange("Cash on Delivery")
                }
            )
            Text(
                text = "Cash on Delivery"
            )
        }
    }
}
