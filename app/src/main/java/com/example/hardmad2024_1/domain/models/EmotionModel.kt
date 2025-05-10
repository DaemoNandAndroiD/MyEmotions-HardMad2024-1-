package com.example.hardmad2024_1.domain.models

import com.example.hardmad2024_1.data.room.entities.EmotionEntity

data class EmotionModel(
    val emotionId: String,
    val icon: Int,
    val name: Int,
    val color: EmotionColor,
    val description: Int
)

fun EmotionEntity.toEmotionModel() = EmotionModel(
    emotionId = this.emotionId,
    icon = this.icon,
    name = this.name,
    color = this.color,
    description = this.description
)
