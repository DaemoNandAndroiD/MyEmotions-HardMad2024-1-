package com.example.hardmad2024_1.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardmad2024_1.domain.use_case.datastore.GetFingerPrintEnabledUseCase
import com.example.hardmad2024_1.domain.use_case.user.AddUserUseCase
import com.example.hardmad2024_1.domain.util.StateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    fun addUser(id : String, name : String){
        viewModelScope.launch {
            addUserUseCase(id, name).collect()
        }
    }
}