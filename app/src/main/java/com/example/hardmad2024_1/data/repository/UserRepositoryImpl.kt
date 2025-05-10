package com.example.hardmad2024_1.data.repository

import com.example.hardmad2024_1.data.room.dao.UserDao
import com.example.hardmad2024_1.data.room.entities.UserEntity
import com.example.hardmad2024_1.domain.interfaces.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUser(userId: String): Flow<UserEntity> {
        return userDao.getUserProfile(userId)
    }

    override suspend fun addUser(userEntity: UserEntity) {
        return userDao.addUser(userEntity)
    }

    override suspend fun editUser(userEntity: UserEntity) {
        return userDao.editUser(userEntity)
    }
}