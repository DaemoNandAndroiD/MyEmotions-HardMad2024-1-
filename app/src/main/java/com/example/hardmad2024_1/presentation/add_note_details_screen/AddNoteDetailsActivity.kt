package com.example.hardmad2024_1.presentation.add_note_details_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.CompoundButton
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.size
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.core.di.qualifiers.SingletonUseCase
import com.example.hardmad2024_1.databinding.AddNoteDetailsActivityBinding
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.use_case.emotion.GetEmotionByIdUseCase
import com.example.hardmad2024_1.domain.use_case.record.AddRecordUseCase
import com.example.hardmad2024_1.domain.use_case.record.GetRecordUseCase
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.activities.MainActivity
import com.example.hardmad2024_1.presentation.add_note_details_screen.util.ChipType
import com.example.hardmad2024_1.presentation.add_note_details_screen.util.OptionChip
import com.example.hardmad2024_1.presentation.welcome_screen.auth.GoogleAuthUiClient
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteDetailsActivity : ComponentActivity() {
    private lateinit var binding: AddNoteDetailsActivityBinding

    private lateinit var viewModel: AddNoteDetailsViewModel

    @Inject
    lateinit var getEmotionByIdUseCase: GetEmotionByIdUseCase

    @Inject
    lateinit var addRecordUseCase: AddRecordUseCase

    @Inject
    lateinit var getRecordUseCase: GetRecordUseCase

    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = AddNoteDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        val recordId = intent.getStringExtra(RECORD_ID_KEY)
        val emotionId = intent.getStringExtra(EMOTION_ID_KEY)

        val savedStateHandle = SavedStateHandle().apply {
            set(RECORD_ID_KEY, recordId)
            set(EMOTION_ID_KEY, emotionId)
        }

        viewModel = ViewModelProvider(
            this, AddNoteDetailsViewModel.Factory(
                applicationContext,
                getEmotionByIdUseCase = getEmotionByIdUseCase,
                addRecordUseCase = addRecordUseCase,
                googleAuthUiClient = googleAuthUiClient,
                getRecordUseCase = getRecordUseCase,
                savedStateHandle = savedStateHandle
            )
        )[AddNoteDetailsViewModel::class]

        observeViewModel()
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
            viewModel.saveRecord()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        binding.addActivityButton.setOnClickListener {
            viewModel.showInputField(ChipType.ACTIVITY)
        }

        binding.addHumanButton.setOnClickListener {
            viewModel.showInputField(ChipType.HUMAN)
        }

        binding.addPlaceButton.setOnClickListener {
            viewModel.showInputField(ChipType.PLACE)
        }

        binding.editTextActivity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val input = binding.editTextActivity.text.toString()
                viewModel.addNewChip(input, ChipType.ACTIVITY)
                true
            } else {
                false
            }
        }

        binding.editTextHuman.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val input = binding.editTextHuman.text.toString()
                viewModel.addNewChip(input, ChipType.HUMAN)
                true
            } else {
                false
            }
        }

        binding.editTextPlace.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val input = binding.editTextPlace.text.toString()
                viewModel.addNewChip(input, ChipType.PLACE)
                true
            } else {
                false
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.activityChipsState.collect { state ->
                when (state) {
                    is StateHandler.Error -> Unit
                    StateHandler.Initial -> Unit
                    StateHandler.Loading -> Unit
                    is StateHandler.Success -> updateActivityChips(state.data)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.placeChipsState.collect { state ->
                when (state) {
                    is StateHandler.Error -> Unit
                    StateHandler.Initial -> Unit
                    StateHandler.Loading -> Unit
                    is StateHandler.Success -> updatePlaceChips(state.data)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.humanChipsState.collect { state ->
                when (state) {
                    is StateHandler.Error -> Unit
                    StateHandler.Initial -> Unit
                    StateHandler.Loading -> Unit
                    is StateHandler.Success -> updateHumanChips(state.data)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.activityInputVisible.collect { isVisible ->
                binding.editTextActivity.visibility = if (isVisible) View.VISIBLE else View.GONE
                binding.addActivityButton.visibility = if (isVisible) View.GONE else View.VISIBLE
                if (!isVisible) binding.editTextActivity.text = null
            }
        }

        lifecycleScope.launch {
            viewModel.humanInputVisible.collect { isVisible ->
                binding.editTextHuman.visibility = if (isVisible) View.VISIBLE else View.GONE
                binding.addHumanButton.visibility = if (isVisible) View.GONE else View.VISIBLE
                if (!isVisible) binding.editTextHuman.text = null
            }
        }

        lifecycleScope.launch {
            viewModel.placeInputVisible.collect { isVisible ->
                binding.editTextPlace.visibility = if (isVisible) View.VISIBLE else View.GONE
                binding.addPlaceButton.visibility = if (isVisible) View.GONE else View.VISIBLE
                if (!isVisible) binding.editTextPlace.text = null
            }
        }

        lifecycleScope.launch {
            viewModel.recordsState.collect {
                if(it != null){
                    setupCard(it)
                }
            }
        }
    }

    private fun updateActivityChips(chips: List<OptionChip>) {
        binding.activityContainer.removeAllViews()

        chips.forEach { chip ->
            binding.activityContainer.addView(createChip(chip))
        }

        binding.activityContainer.addView(binding.editTextActivity)
        binding.activityContainer.addView(binding.addActivityButton)
    }

    private fun updateHumanChips(chips: List<OptionChip>) {
        binding.humanContainer.removeAllViews()

        chips.forEach { chip ->
            binding.humanContainer.addView(createChip(chip))
        }

        binding.humanContainer.addView(binding.editTextHuman)
        binding.humanContainer.addView(binding.addHumanButton)
    }

    private fun updatePlaceChips(chips: List<OptionChip>) {
        binding.placeContainer.removeAllViews()

        chips.forEach { chip ->
            binding.placeContainer.addView(createChip(chip))
        }

        binding.placeContainer.addView(binding.editTextPlace)
        binding.placeContainer.addView(binding.addPlaceButton)
    }

    private fun createChip(chip: OptionChip): Chip {
        return Chip(this).apply {
            text = chip.name
            isCheckable = true
            isChecked = chip.isChecked
            setChipStyle()
            setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
                viewModel.onChipChecked(chip, b)
            }

            val margin = (4 * resources.displayMetrics.density).toInt()
            layoutParams = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(margin, 0, margin, 0)
            }
        }
    }

    private fun Chip.setChipStyle() {
        setTextAppearance(R.style.ChipTextAppearance)
        chipBackgroundColor =
            ContextCompat.getColorStateList(this@AddNoteDetailsActivity, R.color.chip_selector)
    }

    private fun setupCard(record: RecordModel) {
        binding.emotionType.apply {
            text = resources.getString(record.emotionName)
            setTextColor(
                ResourcesCompat.getColor(
                    resources, when (record.emotionColor) {
                        EmotionColor.BLUE -> R.color.blue_text
                        EmotionColor.GREEN -> R.color.green_text
                        EmotionColor.RED -> R.color.red_text
                        EmotionColor.YELLOW -> R.color.yellow_text
                    }, null
                )
            )
        }

        binding.date.text = record.date

        binding.card.background = ResourcesCompat.getDrawable(
            resources, when (record.emotionColor) {
                EmotionColor.BLUE -> R.drawable.card_shape_blue
                EmotionColor.GREEN -> R.drawable.card_shape_green
                EmotionColor.RED -> R.drawable.card_shape_red
                EmotionColor.YELLOW -> R.drawable.card_shape_yellow
            }, null
        )

        binding.icon.setImageResource(record.icon)
    }

    companion object {
        const val RECORD_ID_KEY = "record_id"
        const val EMOTION_ID_KEY = "emotion_id"
    }
}