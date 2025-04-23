package com.example.hardmad2024_1.core.di

import android.content.Context
import androidx.room.Room
import com.example.hardmad2024_1.data.room.EmotionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        EmotionDatabase::class.java,
        "emotion_database"
    ).build()

    @Provides
    fun provideUserDao(db : EmotionDatabase) = db.userDao()

    @Provides
    fun provideNotificationDao(db : EmotionDatabase) = db.notificationDao()

    @Provides
    fun provideRecordDao(db : EmotionDatabase) = db.recordDao()
}