package com.example.hardmad2024_1.presentation.add_note_details_screen

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.use_case.emotion.GetEmotionByIdUseCase
import com.example.hardmad2024_1.domain.use_case.record.AddRecordUseCase
import com.example.hardmad2024_1.domain.use_case.record.GetRecordUseCase
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.add_note_details_screen.util.ChipType
import com.example.hardmad2024_1.presentation.add_note_details_screen.util.InitialChips
import com.example.hardmad2024_1.presentation.add_note_details_screen.util.OptionChip
import com.example.hardmad2024_1.presentation.welcome_screen.auth.GoogleAuthUiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteDetailsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getEmotionByIdUseCase: GetEmotionByIdUseCase,
    private val addRecordUseCase: AddRecordUseCase,
    private val googleAuthUiClient: GoogleAuthUiClient,
    private val getRecordUseCase: GetRecordUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val recordId = savedStateHandle.get<String>(AddNoteDetailsActivity.RECORD_ID_KEY)
    private val emotionId = savedStateHandle.get<String>(AddNoteDetailsActivity.EMOTION_ID_KEY)

    var recordsState = MutableStateFlow<RecordModel?>(null)
        private set

    var activityChipsState = MutableStateFlow<StateHandler<List<OptionChip>>>(StateHandler.Initial)

    var humanChipsState = MutableStateFlow<StateHandler<List<OptionChip>>>(StateHandler.Initial)

    var placeChipsState = MutableStateFlow<StateHandler<List<OptionChip>>>(StateHandler.Initial)

    var activityInputVisible = MutableStateFlow(false)

    var humanInputVisible = MutableStateFlow(false)

    var placeInputVisible = MutableStateFlow(false)

    init {
        initialLoad()
    }

    private fun initialLoad() {
        var activityChips = InitialChips.getActivityChips(context).toMutableList()
        val humanChips = InitialChips.getHumanChips(context).toMutableList()
        val placeChips = InitialChips.getPlaceChips(context).toMutableList()

        if (recordId == null) {
            activityChipsState.value = StateHandler.Success(activityChips)
            humanChipsState.value = StateHandler.Success(humanChips)
            placeChipsState.value = StateHandler.Success(placeChips)
        } else {
            viewModelScope.launch {
                getRecordUseCase(recordId).collect {
                    when (it) {
                        is StateHandler.Error -> Unit
                        StateHandler.Initial -> Unit
                        StateHandler.Loading -> Unit
                        is StateHandler.Success -> {
                            it.data.activities.forEach { name ->
                                val existingChip = activityChips.find { it.name == name }
                                existingChip?.let {
                                    it.isChecked = true
                                } ?: activityChips.add(
                                    OptionChip(
                                        name = name,
                                        isChecked = true,
                                        chipType = ChipType.ACTIVITY
                                    )
                                )
                            }

                            it.data.places.forEach { name ->
                                val existingChip = placeChips.find { it.name == name }
                                existingChip?.let {
                                    it.isChecked = true
                                } ?: placeChips.add(
                                    OptionChip(
                                        name = name,
                                        isChecked = true,
                                        chipType = ChipType.PLACE
                                    )
                                )
                            }

                            it.data.peoples.forEach { name ->
                                val existingChip = humanChips.find { it.name == name }
                                existingChip?.let {
                                    it.isChecked = true
                                } ?: humanChips.add(
                                    OptionChip(
                                        name = name,
                                        isChecked = true,
                                        chipType = ChipType.HUMAN
                                    )
                                )
                            }

                            recordsState.value = it.data.recordModel
                            activityChipsState.value =
                                StateHandler.Success(activityChips)
                            placeChipsState.value =
                                StateHandler.Success(placeChips)
                            humanChipsState.value =
                                StateHandler.Success(humanChips)
                        }
                    }
                }
            }
        }

        if (emotionId != null) {
            viewModelScope.launch {
                recordsState.value = getEmotionByIdUseCase(emotionId).await()
            }
        }
    }

    fun saveRecord() {
        viewModelScope.launch {
            val userId = googleAuthUiClient.getSignedInUser()

            if (userId != null && emotionId != null) {
                val activityList =
                    (activityChipsState.value as StateHandler.Success).data.filter { it.isChecked }
                        .map { it.name }

                val humanList =
                    (humanChipsState.value as StateHandler.Success).data.filter { it.isChecked }
                        .map { it.name }

                val placeList =
                    (placeChipsState.value as StateHandler.Success).data.filter { it.isChecked }
                        .map { it.name }

                recordsState.value?.recordId?.let {
                    addRecordUseCase(
                        userId,
                        emotionId,
                        it,
                        activityList,
                        humanList,
                        placeList
                    ).collect()
                }
            }
        }
    }

    fun onChipChecked(chip: OptionChip, isChecked: Boolean) {
        when (chip.chipType) {
            ChipType.ACTIVITY -> (activityChipsState.value as StateHandler.Success).data.find { it.name == chip.name }?.isChecked =
                isChecked

            ChipType.HUMAN -> (humanChipsState.value as StateHandler.Success).data.find { it.name == chip.name }?.isChecked =
                isChecked

            ChipType.PLACE -> (placeChipsState.value as StateHandler.Success).data.find { it.name == chip.name }?.isChecked =
                isChecked
        }
    }

    fun showInputField(type: ChipType) {
        when (type) {
            ChipType.ACTIVITY -> activityInputVisible.value = true
            ChipType.HUMAN -> humanInputVisible.value = true
            ChipType.PLACE -> placeInputVisible.value = true
        }
    }

    fun addNewChip(name: String, type: ChipType) {
        if (name.isBlank()) {
            when (type) {
                ChipType.ACTIVITY -> activityChipsState.value =
                    StateHandler.Error("Опция не может быть пустым")

                ChipType.HUMAN -> humanChipsState.value =
                    StateHandler.Error("Имя не может быть пустым")

                ChipType.PLACE -> placeChipsState.value =
                    StateHandler.Error("Имя не может быть пустым")
            }
            return
        }

        when (type) {
            ChipType.ACTIVITY -> {
                val currentChips =
                    (activityChipsState.value as? StateHandler.Success)?.data ?: emptyList()
                if (currentChips.any { it.name.equals(name, ignoreCase = true) }) {
                    activityChipsState.value = StateHandler.Error("Такая опция уже есть")
                    return
                }

                val newChip = OptionChip(name = name, chipType = ChipType.ACTIVITY)
                activityChipsState.value = StateHandler.Success(currentChips + newChip)
                activityInputVisible.value = false
            }

            ChipType.HUMAN -> {
                val currentChips =
                    (humanChipsState.value as? StateHandler.Success)?.data ?: emptyList()
                if (currentChips.any { it.name.equals(name, ignoreCase = true) }) {
                    humanChipsState.value = StateHandler.Error("Такая опция уже есть")
                    return
                }

                val newChip = OptionChip(name = name, chipType = ChipType.HUMAN)
                humanChipsState.value = StateHandler.Success(currentChips + newChip)
                humanInputVisible.value = false
            }

            ChipType.PLACE -> {
                val currentChips =
                    (placeChipsState.value as? StateHandler.Success)?.data ?: emptyList()
                if (currentChips.any { it.name.equals(name, ignoreCase = true) }) {
                    placeChipsState.value = StateHandler.Error("Такая опция уже есть")
                    return
                }

                val newChip = OptionChip(name = name, chipType = ChipType.PLACE)
                placeChipsState.value = StateHandler.Success(currentChips + newChip)
                placeInputVisible.value = false
            }
        }
    }

    class Factory constructor(
        private val context: Context,
        private val savedStateHandle: SavedStateHandle,
        private val getEmotionByIdUseCase: GetEmotionByIdUseCase,
        private val addRecordUseCase: AddRecordUseCase,
        private val getRecordUseCase: GetRecordUseCase,
        private val googleAuthUiClient: GoogleAuthUiClient
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return AddNoteDetailsViewModel(
                context = context,
                getEmotionByIdUseCase,
                addRecordUseCase,
                googleAuthUiClient,
                getRecordUseCase,
                savedStateHandle
            ) as T
        }
    }
}