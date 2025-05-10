package com.example.hardmad2024_1.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
)
data class UserEntity(
    @PrimaryKey val userId:String,
    val name:String,
    val avatarPath:String? = null
)
