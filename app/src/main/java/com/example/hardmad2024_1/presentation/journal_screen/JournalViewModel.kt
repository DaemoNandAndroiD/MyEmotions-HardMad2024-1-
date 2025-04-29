package com.example.hardmad2024_1.presentation.journal_screen

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.models.UserModel
import com.example.hardmad2024_1.domain.use_case.record.GetLastRecordsUseCase
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.welcome_screen.auth.GoogleAuthUiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val getLastRecordsUseCase: GetLastRecordsUseCase,
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {
    var recordsState = MutableStateFlow<StateHandler<List<RecordModel>>>(StateHandler.Initial)
        private set

    init {
        getRecords()
    }

    private fun getRecords(){
        val today = Calendar.getInstance()

        val todayEnd = today.clone() as Calendar
        todayEnd.set(Calendar.HOUR_OF_DAY, 23)
        todayEnd.set(Calendar.MINUTE, 59)
        todayEnd.set(Calendar.SECOND, 59)
        todayEnd.set(Calendar.MILLISECOND, 999)

        val yesterdayStart = today.clone() as Calendar
        yesterdayStart.add(Calendar.DAY_OF_MONTH, -1)
        yesterdayStart.set(Calendar.HOUR_OF_DAY, 0)
        yesterdayStart.set(Calendar.MINUTE, 0)
        yesterdayStart.set(Calendar.SECOND, 0)
        yesterdayStart.set(Calendar.MILLISECOND, 0)

        viewModelScope.launch {
            Log.d("dateCheck", todayEnd.time.toString())
            Log.d("dateCheck", yesterdayStart.time.toString())
             googleAuthUiClient.getSignedInUser()?.let {
                 getLastRecordsUseCase(it, yesterdayStart.time, todayEnd.time).collect{ state->
                     recordsState.value = state
                 }
             }
        }
    }
}