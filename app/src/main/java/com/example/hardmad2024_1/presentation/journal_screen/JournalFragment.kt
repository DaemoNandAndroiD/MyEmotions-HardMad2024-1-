package com.example.hardmad2024_1.presentation.journal_screen

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.JournalFragmentBinding
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.domain.models.JournalModel
import com.example.hardmad2024_1.domain.models.NotificationModel
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.add_note_activity.AddNoteActivity
import com.example.hardmad2024_1.presentation.add_note_details_screen.AddNoteDetailsActivity
import com.example.hardmad2024_1.presentation.journal_screen.components.JournalRecordsAdapter
import com.example.hardmad2024_1.presentation.util.classes.ShortNote
import com.example.hardmad2024_1.presentation.util.extensions.toPx
import com.example.hardmad2024_1.presentation.journal_screen.components.CustomProgressView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlin.math.max

@AndroidEntryPoint
class JournalFragment : Fragment() {
    private var _binding: JournalFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: JournalRecordsAdapter
    private val viewModel by viewModels<JournalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = JournalFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        recyclerAdapter = JournalRecordsAdapter(resources, onClick = {
            val newIntent = Intent(requireContext(), AddNoteDetailsActivity::class.java)
            newIntent.putExtra(AddNoteDetailsActivity.RECORD_ID_KEY, it.recordId)
            startActivity(newIntent)
        })
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupRecycler()
        setupOnClickListeners()
    }

    private fun setupRecycler() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.emotionsList.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = recyclerAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.recordsState.combine(viewModel.notificationState) { stateHandler: StateHandler<JournalModel>, stateHandler2: StateHandler<List<NotificationModel>> ->
                    Pair(stateHandler, stateHandler2)
                }.collect { (records, notification) ->
                    when {
                        records is StateHandler.Success && notification is StateHandler.Success -> {
                            recyclerAdapter.loadNewList(records.data.recordModel)
                            setupProgressBar(records.data.recordModel, notification.data.size)

                            binding.perDay.text = resources.getQuantityString(
                                R.plurals.records,
                                notification.data.size,
                                notification.data.size
                            )

                            binding.records.text = resources.getQuantityString(
                                R.plurals.records,
                                records.data.todayRecordsCount,
                                records.data.todayRecordsCount
                            )

                            binding.streak.text = resources.getQuantityString(
                                R.plurals.days,
                                records.data.streak,
                                records.data.streak
                            )
                        }
                    }
                }
            }

        }
    }

    private fun setupProgressBar(list: List<RecordModel>, goal: Int) {
        val params = binding.progressBarContainer.layoutParams
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val calculatedWidth = screenWidth - 48.toPx(requireContext())
        params.width = calculatedWidth
        params.height = calculatedWidth
        binding.progressBarContainer.layoutParams = params

        if (list.isEmpty()) {
            binding.progressBarEmpty.visibility = VISIBLE
            val rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotation_gradient)
            binding.progressBarEmpty.animation = rotateAnimation
        } else {
            binding.progressBarEmpty.visibility = GONE
            val progressView = CustomProgressView(
                requireContext(),
                colors = listOf(
                    ShortNote(
                        color = Color.YELLOW,
                        amount = list.count { it.emotionColor == EmotionColor.YELLOW }),
                    ShortNote(
                        color = Color.RED,
                        amount = list.count { it.emotionColor == EmotionColor.RED }),
                    ShortNote(
                        color = Color.GREEN,
                        amount = list.count { it.emotionColor == EmotionColor.GREEN }),
                    ShortNote(
                        color = Color.BLUE,
                        amount = list.count { it.emotionColor == EmotionColor.BLUE })
                ),
                totalAmount = max(goal, list.size)
            )
            progressView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            binding.progressBarContainer.addView(progressView)
        }

        binding.buttonContainer.visibility = VISIBLE
    }

    private fun setupOnClickListeners() {
        binding.addBtn.setOnClickListener {
            startActivity(Intent(context, AddNoteActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}