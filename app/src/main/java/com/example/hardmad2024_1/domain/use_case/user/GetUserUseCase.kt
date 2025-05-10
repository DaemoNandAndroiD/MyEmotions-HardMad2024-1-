package com.example.hardmad2024_1.domain.use_case.user

import android.util.Log
import com.example.hardmad2024_1.domain.interfaces.UserRepository
import com.example.hardmad2024_1.domain.models.toUserModel
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(id : String) = flow {
        emit(StateHandler.Loading)

        repository.getUser(id)
            .map { it.toUserModel() }
            .collect{entity ->
                emit(StateHandler.Success(entity))
            }
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}