package com.example.hardmad2024_1.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hardmad2024_1.data.room.entities.EmotionEntity
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface RecordDao {
    @Query("SELECT * FROM record JOIN emotion ON emotion.emotionId = record.emotionId WHERE userId = :userId AND record.createdAt BETWEEN :from AND :to")
    fun getEmotions(userId : String, from : Date, to : Date) : Flow<Map<RecordEntity, EmotionEntity>>

    @Insert
    suspend fun addRecord(recordEntity: RecordEntity)

    @Update
    suspend fun editEntity(recordEntity: RecordEntity)
}