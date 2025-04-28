package com.example.hardmad2024_1.domain.models

import com.example.hardmad2024_1.data.room.entities.UserEntity

data class UserModel(
    val id : String,
    val name : String,
    val avatarPath : String? = null
)

fun UserModel.toUserEntity() = UserEntity(
    userId = id,
    name = name,
    avatarPath = avatarPath
)

fun UserEntity.toUserModel() = UserModel(
    id = userId,
    name = name,
    avatarPath = avatarPath
)
