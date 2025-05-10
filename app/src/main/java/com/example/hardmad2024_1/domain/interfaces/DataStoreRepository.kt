package com.example.hardmad2024_1.domain.interfaces

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setFingerPrintEnabled(enabled:Boolean)

    suspend fun setNotificationsEnabled(enabled:Boolean)

    suspend fun getFingerPrintEnabled(): Flow<Boolean>

    suspend fun getNotificationsEnabled(): Flow<Boolean>
}