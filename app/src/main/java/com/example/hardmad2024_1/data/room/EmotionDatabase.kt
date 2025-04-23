package com.example.hardmad2024_1.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hardmad2024_1.data.room.dao.NotificationDao
import com.example.hardmad2024_1.data.room.dao.RecordDao
import com.example.hardmad2024_1.data.room.dao.UserDao
import com.example.hardmad2024_1.data.room.entities.EmotionEntity
import com.example.hardmad2024_1.data.room.entities.NotificationEntity
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import com.example.hardmad2024_1.data.room.entities.UserEntity
import com.example.hardmad2024_1.data.room.entities.type_converters.RecordsTypeConverter

@Database(
    entities = [UserEntity::class, EmotionEntity::class, RecordEntity::class, NotificationEntity::class],
    version = 1
)
@TypeConverters(RecordsTypeConverter::class)
abstract class EmotionDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    abstract fun notificationDao() : NotificationDao
    abstract fun recordDao() : RecordDao
}