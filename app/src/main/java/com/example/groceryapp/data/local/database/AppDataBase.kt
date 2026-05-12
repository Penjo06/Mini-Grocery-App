package com.example.groceryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.groceryapp.data.local.dao.CartDao
import com.example.groceryapp.data.local.entity.CartItemEntity

@Database(
    entities = [CartItemEntity::class],
    version = 1
)

abstract class AppDataBase: RoomDatabase(){

    abstract fun cartDao(): CartDao
}