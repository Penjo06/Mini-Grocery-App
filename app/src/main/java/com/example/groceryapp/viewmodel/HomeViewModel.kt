package com.example.groceryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.data.local.entity.CartItemEntity
import com.example.groceryapp.data.model.DummyDataList
import com.example.groceryapp.data.model.Product
import com.example.groceryapp.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CartRepository
): ViewModel() {
    var searchQuery by mutableStateOf("")
        private set

    var selectedCategory by mutableStateOf("All")
        private set

    fun onSearchChange(query: String){
        searchQuery = query
    }

    fun onCategoryChange(category: String){
        selectedCategory = category
    }


    fun getFilteredProducts(): List<Product>{

        return DummyDataList.products.filter {product ->
            val matchesCategory = selectedCategory == "All" || product.category == selectedCategory

            val matchSearch = product.name.contains(searchQuery, ignoreCase = true)

            matchesCategory && matchSearch

        }
    }

    fun addToCart(product: Product){
        viewModelScope.launch {
           val existingItem = repository.getCartItemById(product.id)

            if (existingItem != null){
                repository.updateCartItem(
                    existingItem.copy(
                        quantity = existingItem.quantity + 1
                    )
                )
            }else{
                repository.insertCartItem(
                    CartItemEntity(
                        id = product.id,
                        name = product.name,
                        price = product.price,
                        image = product.image,
                        quantity = 1
                    )
                )
            }
        }
    }


}