package com.example.groceryapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.groceryapp.navigation.GroceryNav
import com.example.groceryapp.ui.theme.GroceryAppTheme
import com.example.groceryapp.utils.NotificationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationHelper = NotificationHelper(this)
        notificationHelper.createNotificationChannel()
        setContent {
            GroceryAppTheme {
                GroceryNav()
            }
        }
    }
}


