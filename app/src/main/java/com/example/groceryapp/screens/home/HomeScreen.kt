package com.example.groceryapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.groceryapp.data.model.DummyDataList
import com.example.groceryapp.screens.components.CategoryItem
import com.example.groceryapp.screens.components.GroceryAppBar
import com.example.groceryapp.screens.components.ProductCard
import com.example.groceryapp.screens.components.SearchBarSection
import com.example.groceryapp.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        GroceryAppBar(title = "Mini Grocery App", navController = navController)
    }) { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "Welcome find your desired product to order...",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(3.dp))

            SearchBarSection(viewModel.searchQuery){
                viewModel.onSearchChange(it)
            }
            HorizontalDivider(modifier = Modifier.padding(top = 4.dp))

            Spacer(modifier = Modifier.height(4.dp))

            Text("Categories",
                style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(6.dp))

            LazyRow {
                items(DummyDataList.categories){category ->
                    CategoryItem(category = category,
                        isSelected =
                    viewModel.selectedCategory == category){
                       viewModel.onCategoryChange(category)
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(bottom = 6.dp))

            Surface(
                modifier = Modifier.padding(2.dp),
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primary
            ) {
                val filteredProducts = viewModel.getFilteredProducts()

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredProducts) { product ->
                        ProductCard(
                            product = product,
                        ) {
                            viewModel.addToCart(product)
                        }
                    }
                }
            }
        }
    }
}


