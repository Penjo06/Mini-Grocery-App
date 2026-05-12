package com.example.groceryapp.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationHelper(private val context: Context) {

    companion object{
        const val CHANNEL_ID = "otp_channel"
    }

    fun createNotificationChannel(){
        val channel = NotificationChannel(
            CHANNEL_ID,
            "OTP Notifications",
            NotificationManager.IMPORTANCE_HIGH
        )

        channel.description = "channel for otp notifications"

        val manager = context.getSystemService(
            NotificationManager::class.java
        )
        manager.createNotificationChannel(channel)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showOtpNotification(){
        val builder = NotificationCompat.Builder(
            context, CHANNEL_ID
        )
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("OTP Verification")
            .setContentText("Your OTP is: 1234")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)


        NotificationManagerCompat
            .from(context)
            .notify(1, builder.build())
    }
}