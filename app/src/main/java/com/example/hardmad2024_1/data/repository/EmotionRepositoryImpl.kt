package com.example.hardmad2024_1.data.repository

import com.example.hardmad2024_1.data.room.dao.EmotionDao
import com.example.hardmad2024_1.data.room.entities.EmotionEntity
import com.example.hardmad2024_1.domain.interfaces.EmotionRepository
import javax.inject.Inject

class EmotionRepositoryImpl @Inject constructor(
    private val dao: EmotionDao
): EmotionRepository {
    override suspend fun getAllEmotions(): List<EmotionEntity> {
        return dao.getEmotions()
    }

    override suspend fun getEmotion(id:String): EmotionEntity {
        return dao.getEmotion(id)
    }
}