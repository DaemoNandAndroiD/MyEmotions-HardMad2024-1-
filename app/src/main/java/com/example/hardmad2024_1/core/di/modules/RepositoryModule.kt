package com.example.hardmad2024_1.core.di.modules

import com.example.hardmad2024_1.data.data_store.DataStoreManager
import com.example.hardmad2024_1.data.repository.DataStoreRepositoryImpl
import com.example.hardmad2024_1.data.repository.EmotionRepositoryImpl
import com.example.hardmad2024_1.data.repository.NotificationRepositoryImpl
import com.example.hardmad2024_1.data.repository.RecordRepositoryImpl
import com.example.hardmad2024_1.data.repository.UserRepositoryImpl
import com.example.hardmad2024_1.data.room.dao.EmotionDao
import com.example.hardmad2024_1.data.room.dao.NotificationDao
import com.example.hardmad2024_1.data.room.dao.RecordDao
import com.example.hardmad2024_1.data.room.dao.UserDao
import com.example.hardmad2024_1.domain.interfaces.DataStoreRepository
import com.example.hardmad2024_1.domain.interfaces.EmotionRepository
import com.example.hardmad2024_1.domain.interfaces.NotificationRepository
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.interfaces.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNotificationRepository(notificationDao: NotificationDao): NotificationRepository =
        NotificationRepositoryImpl(notificationDao)

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideRecordRepository(recordDao: RecordDao): RecordRepository =
        RecordRepositoryImpl(recordDao)

    @Provides
    @Singleton
    fun providesDataStoreRepository(dataStoreManager: DataStoreManager): DataStoreRepository =
        DataStoreRepositoryImpl(dataStoreManager)

    @Provides
    @Singleton
    fun provideEmotionRepository(emotionDao: EmotionDao): EmotionRepository =
        EmotionRepositoryImpl(emotionDao)
}