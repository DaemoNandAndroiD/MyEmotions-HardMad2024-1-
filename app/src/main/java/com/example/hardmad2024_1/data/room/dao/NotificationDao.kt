package com.example.hardmad2024_1.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hardmad2024_1.data.room.entities.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification WHERE userId = :userId")
    fun getAllNotifications(userId : String): Flow<List<NotificationEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotification(notification : NotificationEntity)

    @Delete
    suspend fun deleteNotification(notification : NotificationEntity)
}