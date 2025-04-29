package com.example.hardmad2024_1.domain.interfaces

import com.example.hardmad2024_1.data.room.entities.EmotionEntity

interface EmotionRepository {
    suspend fun getAllEmotions() : List<EmotionEntity>
}