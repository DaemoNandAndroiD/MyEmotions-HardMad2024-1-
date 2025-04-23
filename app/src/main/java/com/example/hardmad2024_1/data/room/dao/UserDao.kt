package com.example.hardmad2024_1.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hardmad2024_1.data.room.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user : UserEntity)

    @Query("SELECT * FROM user WHERE userId = :userId LIMIT 1")
    fun getUserProfile(userId : String) : Flow<UserEntity>

    @Update
    suspend fun editUser(user : UserEntity)
}