package com.example.hardmad2024_1.core.di

import com.example.hardmad2024_1.domain.interfaces.NotificationRepository
import com.example.hardmad2024_1.domain.interfaces.UserRepository
import com.example.hardmad2024_1.domain.use_case.notification.AddNotificationUseCase
import com.example.hardmad2024_1.domain.use_case.notification.DeleteNotificationUseCase
import com.example.hardmad2024_1.domain.use_case.notification.GetAllNotificationsUseCase
import com.example.hardmad2024_1.domain.use_case.user.AddUserUseCase
import com.example.hardmad2024_1.domain.use_case.user.EditUserUseCase
import com.example.hardmad2024_1.domain.use_case.user.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideAddNotificationUseCase(repository: NotificationRepository) = AddNotificationUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideDeleteNotificationUseCase(repository: NotificationRepository) = DeleteNotificationUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetNotificationUseCase(repository: NotificationRepository) = GetAllNotificationsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetUserUseCase(repository: UserRepository) = GetUserUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideAddUserUseCase(repository: UserRepository) = AddUserUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideEditUserUseCase(repository: UserRepository) = EditUserUseCase(repository)
}