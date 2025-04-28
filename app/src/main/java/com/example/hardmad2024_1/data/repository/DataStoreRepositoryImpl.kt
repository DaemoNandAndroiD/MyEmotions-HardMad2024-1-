package com.example.hardmad2024_1.data.repository

import com.example.hardmad2024_1.data.data_store.DataStoreManager
import com.example.hardmad2024_1.domain.interfaces.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : DataStoreRepository{
    override suspend fun setFingerPrintEnabled(enabled: Boolean) {
        dataStoreManager.setFingerPrintEnabled(enabled)
    }

    override suspend fun setNotificationsEnabled(enabled: Boolean) {
        dataStoreManager.setNotificationEnabled(enabled)
    }

    override suspend fun getFingerPrintEnabled() = dataStoreManager.isFingerPrintEnabled

    override suspend fun getNotificationsEnabled() = dataStoreManager.isNotificationEnabled
}