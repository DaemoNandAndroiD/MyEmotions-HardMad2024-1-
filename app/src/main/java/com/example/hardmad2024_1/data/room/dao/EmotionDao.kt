package com.example.hardmad2024_1.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hardmad2024_1.data.room.entities.EmotionEntity

@Dao
interface EmotionDao {
    @Insert
    suspend fun addEmotions(emotions : List<EmotionEntity>)

    @Query("SELECT * FROM emotion")
    suspend fun getEmotions() : List<EmotionEntity>

    @Query("SELECT * FROM emotion WHERE emotionId=:id LIMIT 1")
    suspend fun getEmotion(id : String) : EmotionEntity
}