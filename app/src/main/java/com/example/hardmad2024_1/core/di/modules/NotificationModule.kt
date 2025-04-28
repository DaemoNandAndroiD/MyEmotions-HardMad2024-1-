package com.example.hardmad2024_1.core.di.modules

import com.example.hardmad2024_1.core.di.qualifiers.SingletonUseCase
import com.example.hardmad2024_1.domain.interfaces.DataStoreRepository
import com.example.hardmad2024_1.domain.use_case.datastore.GetNotificationEnabledUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Provides
    @SingletonUseCase
    fun provideSingletonGetNotificationEnabledUseCase(repository: DataStoreRepository) =
        GetNotificationEnabledUseCase(repository)
}