package com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardmad2024_1.domain.models.StatisticsModel
import com.example.hardmad2024_1.domain.use_case.record.GetMappedRecordsForStatisticsUseCase
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.welcome_screen.auth.GoogleAuthUiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class VerticalStatisticsViewModel @Inject constructor(
    private val getMappedRecordsForStatisticsUseCase: GetMappedRecordsForStatisticsUseCase,
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {
    var recordState = MutableStateFlow<StateHandler<StatisticsModel>>(StateHandler.Initial)
        private set


    fun getData(from : Date,  to : Date){
        googleAuthUiClient.getSignedInUser()?.let {
            viewModelScope.launch {
                getMappedRecordsForStatisticsUseCase(it, from, to).collect{state->
                    recordState.value = state
                }
            }
        }
    }
}