package com.example.hardmad2024_1.presentation.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardmad2024_1.domain.use_case.datastore.GetFingerPrintEnabledUseCase
import com.example.hardmad2024_1.domain.util.StateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getFingerPrintEnabledUseCase: GetFingerPrintEnabledUseCase
): ViewModel() {
    var fingerPrintEnabledState = MutableStateFlow<StateHandler<Boolean>>(StateHandler.Initial)
        private set

    init {
        getFingerPrintEnabled()
    }

    private fun getFingerPrintEnabled() {
        viewModelScope.launch {
            getFingerPrintEnabledUseCase().collect {
                fingerPrintEnabledState.value = it
            }
        }
    }
}