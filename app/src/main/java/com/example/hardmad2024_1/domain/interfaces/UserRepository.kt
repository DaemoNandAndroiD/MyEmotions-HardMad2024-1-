package com.example.hardmad2024_1.domain.interfaces

import com.example.hardmad2024_1.data.room.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(userId : String) : Flow<UserEntity>

    suspend fun addUser(userEntity: UserEntity)

    suspend fun editUser(userEntity: UserEntity)
}