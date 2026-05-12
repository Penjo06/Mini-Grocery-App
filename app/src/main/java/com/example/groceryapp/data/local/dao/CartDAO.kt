package com.example.groceryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.groceryapp.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart_items")
    fun getCartItems():
            Flow<List<CartItemEntity>>

    @Query("SELECT * FROM cart_items Where id = :id")
    suspend fun getCartItemById(id: Int): CartItemEntity?

    @Update
    suspend fun updateCartItem(
        item: CartItemEntity
    )

    @Delete
    suspend fun deleteCartItem(
        item: CartItemEntity
    )

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}