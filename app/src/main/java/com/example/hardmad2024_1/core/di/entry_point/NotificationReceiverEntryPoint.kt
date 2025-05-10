package com.example.hardmad2024_1.core.di.entry_point

import com.example.hardmad2024_1.core.di.qualifiers.SingletonUseCase
import com.example.hardmad2024_1.domain.use_case.datastore.GetNotificationEnabledUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NotificationReceiverEntryPoint {
    @SingletonUseCase
    fun getNotificationEnabledUseCase(): GetNotificationEnabledUseCase
}