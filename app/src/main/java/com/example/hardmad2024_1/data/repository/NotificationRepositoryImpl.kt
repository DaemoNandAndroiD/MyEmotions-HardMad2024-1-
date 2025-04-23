package com.example.hardmad2024_1.data.repository

import com.example.hardmad2024_1.data.room.dao.NotificationDao
import com.example.hardmad2024_1.data.room.entities.NotificationEntity
import com.example.hardmad2024_1.domain.interfaces.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val dao : NotificationDao
) : NotificationRepository{
    override suspend fun getAllNotifications(userId : String): Flow<List<NotificationEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun addNotification(notificationEntity: NotificationEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNotification(notification: NotificationEntity) {
        TODO("Not yet implemented")
    }

}