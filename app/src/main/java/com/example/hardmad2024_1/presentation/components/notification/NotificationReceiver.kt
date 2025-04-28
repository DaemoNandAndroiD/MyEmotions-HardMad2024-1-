package com.example.hardmad2024_1.presentation.components.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.core.di.entry_point.NotificationReceiverEntryPoint
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.activities.AddNoteActivity
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar

class NotificationReceiver : BroadcastReceiver() {
    companion object {
        const val CHANNEL_ID = "EMOTION_CHANNEL_ID"
        const val CHANNEL_NAME = "EMOTION_CHANNEL"
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, p1: Intent?) {
        if (context == null) return

        scheduleNextNotification(context)

        val entryPoint = EntryPointAccessors.fromApplication<NotificationReceiverEntryPoint>(context)
        val getNotificationEnabledUseCase = entryPoint.getNotificationEnabledUseCase()

        CoroutineScope(Dispatchers.Main).launch {
            val result = getNotificationEnabledUseCase().drop(1).first()
            if (result is StateHandler.Success && result.data) {
                postNotification(context)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun postNotification(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)

        val intent = Intent(context, AddNoteActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_app)
            .setContentTitle("Your Reminder")
            .setContentText("How was your day?")
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun scheduleNextNotification(context: Context) {
        ReminderManager.scheduleReminder(
            context,
            Calendar.getInstance().time.hours,
            Calendar.getInstance().time.minutes
        )
    }
}
