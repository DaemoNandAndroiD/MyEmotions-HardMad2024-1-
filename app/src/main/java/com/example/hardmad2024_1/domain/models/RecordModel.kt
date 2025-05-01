package com.example.hardmad2024_1.domain.models

import com.example.hardmad2024_1.data.room.entities.EmotionEntity
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import java.util.Calendar
import java.util.UUID

data class RecordModel(
    val emotionName: Int,
    val emotionColor: EmotionColor,
    val date: String,
    val icon: Int,
    val recordId : String
)

fun EmotionEntity.toRecordModel(date: String) =
    RecordModel(
        emotionName = name,
        emotionColor = color,
        date = date,
        icon = icon,
        recordId = UUID.randomUUID().toString()
    )


