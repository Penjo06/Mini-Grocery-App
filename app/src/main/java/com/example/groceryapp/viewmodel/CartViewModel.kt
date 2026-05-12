package com.example.groceryapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.data.local.entity.CartItemEntity
import com.example.groceryapp.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository): ViewModel() {

        val cartItems = cartRepository
            .getCartItems()
            .stateIn(scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun addToCart(
        item: CartItemEntity
    ){
        viewModelScope.launch {
            cartRepository.insertCartItem(item)
        }
    }

    fun updateCartItem(
        item: CartItemEntity
    ){
        viewModelScope.launch {
            cartRepository.updateCartItem(item)
        }
    }

    fun deleteCartItem(
        item: CartItemEntity
    ){
        viewModelScope.launch {
            cartRepository.deleteCartItem(item)
        }
    }

    fun clearCart(){
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }

    fun increaseQuantity(item: CartItemEntity){
        viewModelScope.launch {

            cartRepository.updateCartItem(
                item.copy(
                    quantity = item.quantity + 1
                )
            )
        }
        Log.d("cart_item", "quantity increased")
    }

    fun decreaseQuantity(item: CartItemEntity){
        viewModelScope.launch {
            if(item.quantity > 1){
                cartRepository.updateCartItem(
                    item.copy(
                        quantity = item.quantity - 1
                    )
                )
                Log.d("cart_item", "quantity decreased")
            }else{
                cartRepository.deleteCartItem(item)

                Log.d("cart_item", "item deleted")
            }
        }
    }

}