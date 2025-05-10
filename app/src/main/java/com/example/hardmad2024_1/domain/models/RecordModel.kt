package com.example.hardmad2024_1.domain.models

import com.example.hardmad2024_1.data.room.entities.EmotionEntity
import java.util.UUID


data class RecordModel(
    val emotionId : String,
    val emotionName: Int,
    val emotionColor: EmotionColor,
    val date: String,
    val icon: Int,
    val recordId : String
)

fun EmotionEntity.toRecordModel(date: String) =
    RecordModel(
        emotionId = emotionId,
        emotionName = name,
        emotionColor = color,
        date = date,
        icon = icon,
        recordId = UUID.randomUUID().toString(),
    )


