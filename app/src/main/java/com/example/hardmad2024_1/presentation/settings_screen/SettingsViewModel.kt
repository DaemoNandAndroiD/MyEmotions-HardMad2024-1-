package com.example.hardmad2024_1.presentation.settings_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardmad2024_1.core.di.qualifiers.ViewModelUseCase
import com.example.hardmad2024_1.domain.models.NotificationModel
import com.example.hardmad2024_1.domain.models.UserModel
import com.example.hardmad2024_1.domain.use_case.datastore.GetFingerPrintEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.datastore.GetNotificationEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.datastore.SetFingerPrintEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.datastore.SetNotificationEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.notification.AddNotificationUseCase
import com.example.hardmad2024_1.domain.use_case.notification.DeleteNotificationUseCase
import com.example.hardmad2024_1.domain.use_case.notification.GetAllNotificationsUseCase
import com.example.hardmad2024_1.domain.use_case.user.EditUserUseCase
import com.example.hardmad2024_1.domain.use_case.user.GetUserUseCase
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.welcome_screen.auth.GoogleAuthUiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val editUserUseCase: EditUserUseCase,
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
    private val addNotificationUseCase: AddNotificationUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    @ViewModelUseCase private val getNotificationEnabledUseCase: GetNotificationEnabledUseCase,
    private val getFingerPrintEnabledUseCase: GetFingerPrintEnabledUseCase,
    private val setNotificationEnabledUseCase: SetNotificationEnabledUseCase,
    private val setFingerPrintEnabledUseCase: SetFingerPrintEnabledUseCase,
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {
    var userState = MutableStateFlow<StateHandler<UserModel>>(StateHandler.Initial)
        private set

    var notificationState = MutableStateFlow<StateHandler<List<NotificationModel>>>(StateHandler.Initial)
        private set

    var notificationEnabledState = MutableStateFlow<StateHandler<Boolean>>(StateHandler.Initial)
        private set

    var fingerPrintEnabledState = MutableStateFlow<StateHandler<Boolean>>(StateHandler.Initial)
        private set

    init {
        getUser()
        getNotifications()
        getFingerPrintEnabled()
        getNotificationEnabled()
    }


    private fun getUser(){
        viewModelScope.launch {
            googleAuthUiClient.getSignedInUser()?.let { id ->
                getUserUseCase(id).collect {
                    userState.value = it
                }
            }
        }
    }

    fun editUser(newPath : String){
        val currentUser = (userState.value as? StateHandler.Success)?.data

        currentUser?.let {
            viewModelScope.launch {
                editUserUseCase(it.copy(avatarPath = newPath)).collect()
            }
        }
    }

    private fun getNotifications(){
        viewModelScope.launch {
            googleAuthUiClient.getSignedInUser()?.let {id->
                getAllNotificationsUseCase(id).collect{
                    notificationState.value = it
                }
            }
        }
    }

    fun deleteNotification(index : Int){
        val currentList = (notificationState.value as? StateHandler.Success)?.data

        currentList?.let {
            viewModelScope.launch {
                googleAuthUiClient.getSignedInUser()?.let {id->
                    deleteNotificationUseCase(id, it[index]).collect()
                }
            }
        }
    }

    fun addNotification(time : String){
        viewModelScope.launch {
            googleAuthUiClient.getSignedInUser()?.let { id ->
                addNotificationUseCase(id, time).collect()
            }
        }
    }

    private fun getNotificationEnabled() {
        viewModelScope.launch {
            getNotificationEnabledUseCase().collect {
                notificationEnabledState.value = it
            }
        }
    }

    fun setNotificationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            setNotificationEnabledUseCase(enabled).collect()
        }
    }

    private fun getFingerPrintEnabled() {
        viewModelScope.launch {
            getFingerPrintEnabledUseCase().collect {
                fingerPrintEnabledState.value = it
            }
        }
    }

    fun setFingerPrintEnabled(enabled: Boolean) {
        viewModelScope.launch {
            setFingerPrintEnabledUseCase(enabled).collect()
        }
    }

    fun signOut(){
        googleAuthUiClient.signOut()
    }
}