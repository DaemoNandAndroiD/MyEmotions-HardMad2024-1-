package com.example.hardmad2024_1.presentation.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.hardmad2024_1.R

class NotificationReceiver : BroadcastReceiver(){
    companion object {
        const val CHANNEL_ID = "EMOTION_CHANNEL_ID"
        const val CHANNEL_NAME = "EMOTION_CHANNEL"
    }

    override fun onReceive(context: Context?, p1: Intent?) {
        val notificationManager  = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_app)
            .setContentTitle("Your Reminder")
            .setContentText("How was your day?")
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(1, builder.build())
    }
}
