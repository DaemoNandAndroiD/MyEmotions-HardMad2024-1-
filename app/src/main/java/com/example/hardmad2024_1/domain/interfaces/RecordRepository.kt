package com.example.hardmad2024_1.domain.interfaces

import com.example.hardmad2024_1.data.room.entities.EmotionEntity
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface RecordRepository {
    suspend fun getEmotions(userId : String, from : Date, to : Date) : Flow<Map<RecordEntity, EmotionEntity>>

    suspend fun addRecord(recordEntity: RecordEntity)

    suspend fun editEntity(recordEntity: RecordEntity)
}