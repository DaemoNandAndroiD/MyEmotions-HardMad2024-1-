package com.example.hardmad2024_1.domain.use_case.user

import com.example.hardmad2024_1.domain.interfaces.UserRepository
import com.example.hardmad2024_1.domain.models.UserModel
import com.example.hardmad2024_1.domain.models.toUserEntity
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(id : String, name : String) = flow {
        emit(StateHandler.Loading)

        repository.addUser(UserModel(id, name).toUserEntity())

        emit(StateHandler.Success(Unit))
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}