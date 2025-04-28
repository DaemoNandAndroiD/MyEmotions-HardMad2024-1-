package com.example.hardmad2024_1.domain.use_case.user

import android.util.Log
import com.example.hardmad2024_1.domain.interfaces.UserRepository
import com.example.hardmad2024_1.domain.models.UserModel
import com.example.hardmad2024_1.domain.models.toUserEntity
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EditUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(user: UserModel) = flow {
        Log.d("here", "4")
        emit(StateHandler.Loading)

        repository.editUser(user.toUserEntity())

        emit(StateHandler.Success(Unit))
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}