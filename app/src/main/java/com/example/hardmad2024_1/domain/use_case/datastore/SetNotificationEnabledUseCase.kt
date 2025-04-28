package com.example.hardmad2024_1.domain.use_case.datastore

import com.example.hardmad2024_1.domain.interfaces.DataStoreRepository
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetNotificationEnabledUseCase @Inject constructor(
    private val repository: DataStoreRepository
) {
    operator fun invoke(enabled: Boolean) = flow {
        emit(StateHandler.Loading)

        repository.setNotificationsEnabled(enabled)

        emit(StateHandler.Success(Unit))
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}
