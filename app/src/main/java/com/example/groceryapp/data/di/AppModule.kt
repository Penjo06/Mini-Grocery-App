package com.example.groceryapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.groceryapp.data.local.dao.CartDao
import com.example.groceryapp.data.local.database.AppDataBase
import com.example.groceryapp.data.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCartDao(
        database: AppDataBase
    ): CartDao{
        return database.cartDao()
    }

    @Provides
    @Singleton
    fun provideCartRepository(
        cartDao: CartDao
    ): CartRepository{
        return CartRepository(cartDao)
    }
}