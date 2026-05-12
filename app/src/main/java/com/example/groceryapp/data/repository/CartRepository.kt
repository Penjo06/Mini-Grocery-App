package com.example.groceryapp.data.repository

import com.example.groceryapp.data.local.dao.CartDao
import com.example.groceryapp.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

class CartRepository(
    private val cartDao: CartDao
) {
    suspend fun insertCartItem(
        item: CartItemEntity
    ){
        cartDao.insertCartItem(item)
    }

    fun getCartItems(): Flow<List<CartItemEntity>> {
        return cartDao.getCartItems()
    }

    suspend fun getCartItemById(id: Int): CartItemEntity?{
        return cartDao.getCartItemById(id)
    }

    suspend fun updateCartItem(
        item: CartItemEntity
    ){
        cartDao.updateCartItem(item)
    }

    suspend fun deleteCartItem(
        item: CartItemEntity
    ){
        cartDao.deleteCartItem(item)
    }

    suspend fun clearCart(){
        cartDao.clearCart()
    }
}