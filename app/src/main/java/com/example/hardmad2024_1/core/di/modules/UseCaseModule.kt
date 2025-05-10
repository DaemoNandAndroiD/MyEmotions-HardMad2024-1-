package com.example.hardmad2024_1.core.di.modules

import android.content.Context
import com.example.hardmad2024_1.core.di.qualifiers.ViewModelUseCase
import com.example.hardmad2024_1.domain.interfaces.DataStoreRepository
import com.example.hardmad2024_1.domain.interfaces.EmotionRepository
import com.example.hardmad2024_1.domain.interfaces.NotificationRepository
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.interfaces.UserRepository
import com.example.hardmad2024_1.domain.mapper.DateMapper
import com.example.hardmad2024_1.domain.use_case.datastore.GetFingerPrintEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.datastore.GetNotificationEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.datastore.SetFingerPrintEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.datastore.SetNotificationEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.emotion.GetEmotionByIdUseCase
import com.example.hardmad2024_1.domain.use_case.emotion.GetEmotionsUseCase
import com.example.hardmad2024_1.domain.use_case.notification.AddNotificationUseCase
import com.example.hardmad2024_1.domain.use_case.notification.DeleteNotificationUseCase
import com.example.hardmad2024_1.domain.use_case.notification.GetAllNotificationsUseCase
import com.example.hardmad2024_1.domain.use_case.record.AddRecordUseCase
import com.example.hardmad2024_1.domain.use_case.record.EditRecordUseCase
import com.example.hardmad2024_1.domain.use_case.record.GetLastRecordsUseCase
import com.example.hardmad2024_1.domain.use_case.record.GetMappedRecordsForStatisticsUseCase
import com.example.hardmad2024_1.domain.use_case.record.GetRecordUseCase
import com.example.hardmad2024_1.domain.use_case.user.AddUserUseCase
import com.example.hardmad2024_1.domain.use_case.user.EditUserUseCase
import com.example.hardmad2024_1.domain.use_case.user.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideAddNotificationUseCase(repository: NotificationRepository) =
        AddNotificationUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideDeleteNotificationUseCase(repository: NotificationRepository) =
        DeleteNotificationUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetNotificationUseCase(repository: NotificationRepository) =
        GetAllNotificationsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetUserUseCase(repository: UserRepository) = GetUserUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideAddUserUseCase(repository: UserRepository) = AddUserUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideEditUserUseCase(repository: UserRepository) = EditUserUseCase(repository)

    @Provides
    @ViewModelScoped
    @ViewModelUseCase
    fun provideGetNotificationEnabledUseCase(repository: DataStoreRepository) =
        GetNotificationEnabledUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideSetNotificationEnabledUseCase(repository: DataStoreRepository) =
        SetNotificationEnabledUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetFingerPrintEnabledUseCase(repository: DataStoreRepository) =
        GetFingerPrintEnabledUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideSetFingerPrintEnabledUseCase(repository: DataStoreRepository) =
        SetFingerPrintEnabledUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideDateMapper(@ApplicationContext context: Context) = DateMapper(context)

    @Provides
    @ViewModelScoped
    fun provideLastRecordsUseCase(repository: RecordRepository, dateMapper: DateMapper) =
        GetLastRecordsUseCase(repository, dateMapper)

    @Provides
    @ViewModelScoped
    fun provideGetEmotionsUseCase(repository: EmotionRepository) = GetEmotionsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetEmotionByIdUseCase(repository: EmotionRepository, dateMapper: DateMapper) =
        GetEmotionByIdUseCase(repository, dateMapper)

    @Provides
    @ViewModelScoped
    fun provideAddRecordUseCase(repository: RecordRepository) = AddRecordUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetRecordUseCase(repository: RecordRepository, dateMapper: DateMapper) =
        GetRecordUseCase(repository, dateMapper)

    @Provides
    @ViewModelScoped
    fun provideEditRecordUseCase(repository: RecordRepository, dateMapper: DateMapper) =
        EditRecordUseCase(repository, dateMapper)

    @Provides
    @ViewModelScoped
    fun provideGetMappedRecordsUseCase(repository: RecordRepository, dateMapper: DateMapper) =
        GetMappedRecordsForStatisticsUseCase(repository, dateMapper)
}