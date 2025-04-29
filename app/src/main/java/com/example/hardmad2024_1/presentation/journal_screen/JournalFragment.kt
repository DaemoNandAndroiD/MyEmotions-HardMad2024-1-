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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.JournalFragmentBinding
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.add_note_activity.AddNoteActivity
import com.example.hardmad2024_1.presentation.journal_screen.components.JournalRecordsAdapter
import com.example.hardmad2024_1.presentation.util.classes.ShortNote
import com.example.hardmad2024_1.presentation.util.extensions.toPx
import com.example.hardmad2024_1.presentation.journal_screen.components.CustomProgressView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JournalFragment : Fragment() {
    private var _binding: JournalFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter : JournalRecordsAdapter
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

        recyclerAdapter = JournalRecordsAdapter(resources, onClick = {})
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.records.text = this.resources.getQuantityString(R.plurals.records, 5, 5)
        binding.perDay.text = this.resources.getQuantityString(R.plurals.records, 2, 2)
        binding.streak.text = this.resources.getQuantityString(R.plurals.days, 3, 3)

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
            viewModel.recordsState.collect { state ->
                when (state) {
                    is StateHandler.Error -> Unit
                    StateHandler.Initial -> Unit
                    StateHandler.Loading -> Unit
                    is StateHandler.Success -> {
                        recyclerAdapter.loadNewList(newItems = state.data)
                    }
                }
            }
        }
    }

    private fun setupProgressBar(isEmpty: Boolean) {
        val params = binding.progressBarContainer.layoutParams
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val calculatedWidth = screenWidth - 48.toPx(requireContext())
        params.width = calculatedWidth
        params.height = calculatedWidth
        binding.progressBarContainer.layoutParams = params

        if (requireActivity().intent.getBooleanExtra("isEmpty", true)) {
            binding.progressBarEmpty.visibility = VISIBLE
            val rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotation_gradient)
            binding.progressBarEmpty.animation = rotateAnimation
        } else {
            binding.progressBarEmpty.visibility = GONE
            val progressView = CustomProgressView(
                requireContext(),
                colors = listOf(
                    ShortNote(color = Color.YELLOW, amount = 4),
                    ShortNote(color = Color.RED, amount = 1),
                    ShortNote(color = Color.GREEN, amount = 2),
                    ShortNote(color = Color.BLUE, amount = 1)
                ),
                totalAmount = 10
            )
            progressView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            binding.progressBarContainer.addView(progressView)
        }
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