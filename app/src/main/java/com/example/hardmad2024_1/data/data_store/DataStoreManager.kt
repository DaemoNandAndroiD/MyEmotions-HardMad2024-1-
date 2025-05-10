package com.example.hardmad2024_1.data.data_store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit

import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val dataStoreName = "EMOTION_SETTINGS"

private val Context.dataStore by preferencesDataStore(dataStoreName)

class DataStoreManager @Inject constructor(
    private val context: Context
) {
    private val isNotificationEnabledKey = booleanPreferencesKey("notification")
    private val isFingerPrintEnabledKey = booleanPreferencesKey("finger_print")

    val isNotificationEnabled = context.dataStore.data
        .map {
            it[isNotificationEnabledKey] ?: false
        }

    val isFingerPrintEnabled = context.dataStore.data
        .map {
            it[isFingerPrintEnabledKey] ?: false
        }

    suspend fun setNotificationEnabled(enabled:Boolean){
        context.dataStore.edit {
            it[isNotificationEnabledKey] = enabled
        }
    }

    suspend fun setFingerPrintEnabled(enabled:Boolean){
        context.dataStore.edit {
            it[isFingerPrintEnabledKey] = enabled
        }
    }
}