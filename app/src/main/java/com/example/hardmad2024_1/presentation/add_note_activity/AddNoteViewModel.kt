package com.example.hardmad2024_1.presentation.add_note_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardmad2024_1.domain.models.EmotionModel
import com.example.hardmad2024_1.domain.use_case.emotion.GetEmotionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val getEmotionsUseCase: GetEmotionsUseCase
) : ViewModel() {
    var state = MutableStateFlow<List<EmotionModel>>(listOf())

    init {
        getEmotions()
    }


    private fun getEmotions(){
        viewModelScope.launch {
            val deffered = getEmotionsUseCase()
            state.value = deffered.await()
        }
    }
}