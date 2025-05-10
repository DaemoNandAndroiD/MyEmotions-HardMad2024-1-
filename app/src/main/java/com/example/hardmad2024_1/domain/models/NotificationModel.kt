package com.example.hardmad2024_1.domain.models

import com.example.hardmad2024_1.data.room.entities.NotificationEntity

data class NotificationModel(
    val id : String,
    val time : String
)

fun NotificationModel.toNotificationEntity(userId : String) = NotificationEntity(
    notificationId = id,
    userId = userId,
    time = time
)

fun NotificationEntity.toNotificationModel() = NotificationModel(
    id = notificationId,
    time = time
)


