package com.example.hardmad2024_1.data.repository

import android.util.Log
import com.example.hardmad2024_1.data.room.dao.NotificationDao
import com.example.hardmad2024_1.data.room.entities.NotificationEntity
import com.example.hardmad2024_1.domain.interfaces.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val dao : NotificationDao
) : NotificationRepository{
    override suspend fun getAllNotifications(userId : String): Flow<List<NotificationEntity>> {
        return dao.getAllNotifications(userId)
    }

    override suspend fun addNotification(notificationEntity: NotificationEntity) {
        dao.addNotification(notificationEntity)
    }

    override suspend fun deleteNotification(notification: NotificationEntity) {
        dao.deleteNotification(notification)
    }

}