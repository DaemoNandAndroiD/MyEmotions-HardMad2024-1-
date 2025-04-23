package com.example.hardmad2024_1.domain.interfaces

import com.example.hardmad2024_1.data.room.entities.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun getAllNotifications(userId : String) : Flow<List<NotificationEntity>>

    suspend fun addNotification(notificationEntity: NotificationEntity)

    suspend fun deleteNotification(notification : NotificationEntity)
}