package com.example.hardmad2024_1.presentation.statistics.week_statistics

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.StatisticsWeekBinding
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment.VerticalStatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeekStatisticsFragment : Fragment(R.layout.statistics_week) {
    private lateinit var binding: StatisticsWeekBinding
    private val viewModel by viewModels<VerticalStatisticsViewModel>({ requireParentFragment() })
    private lateinit var recyclerAdapter : WeekRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        recyclerAdapter = WeekRecyclerAdapter(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsWeekBinding.bind(view)

        observeViewModel()
        setupRecycler()
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.recordState.collect{state->
                    when(state){
                        is StateHandler.Error -> Unit
                        StateHandler.Initial -> Unit
                        StateHandler.Loading -> Unit
                        is StateHandler.Success -> recyclerAdapter.loadNewItems(state.data.weekData)
                    }
                }
            }
        }
    }

    private fun setupRecycler(){
        val recyclerView = binding.recordsContainer
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter
    }
}