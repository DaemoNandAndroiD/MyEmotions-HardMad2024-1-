package com.example.hardmad2024_1.domain.use_case.notification

import com.example.hardmad2024_1.domain.interfaces.NotificationRepository
import com.example.hardmad2024_1.domain.models.NotificationModel
import com.example.hardmad2024_1.domain.models.toNotificationEntity
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddNotificationUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    operator fun invoke(userid : String, notificationModel: NotificationModel) = flow {
        emit(StateHandler.Loading)

        repository.addNotification(notificationModel.toNotificationEntity(userid))

        emit(StateHandler.Success(Unit))
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}