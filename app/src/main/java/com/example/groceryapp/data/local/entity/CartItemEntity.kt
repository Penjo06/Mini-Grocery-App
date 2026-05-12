package com.example.groceryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart_items")
data class CartItemEntity(

    @PrimaryKey
    val id: Int,

    val name: String,

    val price: Double,

    val image: String,

    val quantity: Int

)
