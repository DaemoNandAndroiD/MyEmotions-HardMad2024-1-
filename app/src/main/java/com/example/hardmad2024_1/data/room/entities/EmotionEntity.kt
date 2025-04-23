package com.example.hardmad2024_1.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hardmad2024_1.domain.models.EmotionColor

@Entity(
    tableName = "emotion"
)
data class EmotionEntity(
    @PrimaryKey val emotionId : String,
    val icon : String,
    val name : String,
    val color : EmotionColor
)



