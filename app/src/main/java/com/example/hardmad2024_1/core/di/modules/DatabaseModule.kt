package com.example.hardmad2024_1.core.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hardmad2024_1.data.room.EmotionDatabase
import com.example.hardmad2024_1.data.room.dao.EmotionDao
import com.example.hardmad2024_1.data.room.entities.emotionsList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) = EmotionDatabase.getInstance(context)

    @Provides
    fun provideUserDao(db: EmotionDatabase) = db.userDao()

    @Provides
    fun provideNotificationDao(db: EmotionDatabase) = db.notificationDao()

    @Provides
    fun provideRecordDao(db: EmotionDatabase) = db.recordDao()
    @Provides
    fun provideEmotionDao(db: EmotionDatabase) = db.emotionDao()
}