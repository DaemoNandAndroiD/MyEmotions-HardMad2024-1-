package com.example.hardmad2024_1.data.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import java.util.Date

@Entity(
    tableName = "record",
    primaryKeys = ["userId", "recordId"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EmotionEntity::class,
            parentColumns = ["emotionId"],
            childColumns = ["emotionId"]
        )
    ]
)
data class RecordEntity(
    val userId: String,
    val emotionId : String,
    val recordId: String,
    val recordDetails: RecordDetails,
    val createdAt: Date
)

data class RecordDetails(
    val activities: List<String>,
    val peoples: List<String>,
    val places: List<String>
)
