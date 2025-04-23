package com.example.hardmad2024_1.data.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notification",
    primaryKeys = ["userId", "notificationId"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotificationEntity(
    val userId : String,
    val notificationId : String,
    val time : String,
)