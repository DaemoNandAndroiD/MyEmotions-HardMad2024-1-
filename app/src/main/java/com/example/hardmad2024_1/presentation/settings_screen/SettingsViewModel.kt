package com.example.hardmad2024_1.presentation.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardmad2024_1.domain.models.UserModel
import com.example.hardmad2024_1.domain.use_case.notification.AddNotificationUseCase
import com.example.hardmad2024_1.domain.use_case.notification.DeleteNotificationUseCase
import com.example.hardmad2024_1.domain.use_case.notification.GetAllNotificationsUseCase
import com.example.hardmad2024_1.domain.use_case.user.EditUserUseCase
import com.example.hardmad2024_1.domain.use_case.user.GetUserUseCase
import com.example.hardmad2024_1.domain.util.StateHandler
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
    private val deleteNotificationUseCase: DeleteNotificationUseCase
) : ViewModel() {
    var userState = MutableStateFlow<StateHandler<UserModel>>(StateHandler.Initial)
        private set

    init {
        getUser()
    }

    var notificationState = MutableStateFlow(StateHandler.Initial)
        private set

    private fun getUser(id : String = "0"){
        viewModelScope.launch {
            getUserUseCase(id).collect {
                userState.value = it
            }
        }
    }
}