package com.example.hardmad2024_1.domain.use_case.notification

import com.example.hardmad2024_1.domain.interfaces.NotificationRepository
import com.example.hardmad2024_1.domain.models.toNotificationModel
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllNotificationsUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    operator fun invoke(userId : String) = flow{
        emit(StateHandler.Loading)

        repository.getAllNotifications(userId)
            .map {list->
                list.map {
                    it.toNotificationModel()
                }
            }
            .collect{
                emit(StateHandler.Success(it))
            }
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}