package com.example.hardmad2024_1.presentation.statistics.often_statistics

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
import com.example.hardmad2024_1.databinding.StatisticsOftenBinding
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment.VerticalStatisticsViewModel
import kotlinx.coroutines.launch

class OftenStatisticsFragment:Fragment(R.layout.statistics_often) {
    private lateinit var binding:StatisticsOftenBinding
    private val viewModel by viewModels<VerticalStatisticsViewModel>({requireParentFragment()})
    private lateinit var recyclerAdapter : OftenElementsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        recyclerAdapter = OftenElementsAdapter(resources)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = StatisticsOftenBinding.bind(view)

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
                        is StateHandler.Success -> recyclerAdapter.loadNewItems(state.data.oftenData.take(7))
                    }
                }
            }
        }
    }

    private fun setupRecycler(){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.emotionsContainer.apply {
            this.layoutManager = layoutManager
            this.adapter = recyclerAdapter
        }
    }
}