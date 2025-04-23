package com.example.hardmad2024_1.data.repository

import com.example.hardmad2024_1.data.room.dao.RecordDao
import com.example.hardmad2024_1.data.room.entities.EmotionEntity
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(
    private val recordDao: RecordDao
): RecordRepository {
    override suspend fun getEmotions(
        userId: String,
        from: Date,
        to: Date
    ): Flow<List<Map<RecordEntity, EmotionEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun addRecord(recordEntity: RecordEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun editEntity(recordEntity: RecordEntity) {
        TODO("Not yet implemented")
    }
}