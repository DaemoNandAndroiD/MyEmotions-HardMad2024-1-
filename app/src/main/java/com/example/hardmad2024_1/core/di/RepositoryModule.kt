package com.example.hardmad2024_1.core.di

import com.example.hardmad2024_1.data.repository.NotificationRepositoryImpl
import com.example.hardmad2024_1.data.repository.RecordRepositoryImpl
import com.example.hardmad2024_1.data.repository.UserRepositoryImpl
import com.example.hardmad2024_1.data.room.dao.NotificationDao
import com.example.hardmad2024_1.data.room.dao.RecordDao
import com.example.hardmad2024_1.data.room.dao.UserDao
import com.example.hardmad2024_1.domain.interfaces.NotificationRepository
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.interfaces.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideNotificationRepository(notificationDao: NotificationDao): NotificationRepository =
        NotificationRepositoryImpl(notificationDao)

    @Provides
    @ViewModelScoped
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @ViewModelScoped
    fun provideRecordRepository(recordDao: RecordDao): RecordRepository = RecordRepositoryImpl(recordDao)
}