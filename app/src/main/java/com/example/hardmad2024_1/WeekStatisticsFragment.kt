package com.example.hardmad2024_1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardmad2024_1.databinding.StatisticsWeekBinding
import com.example.hardmad2024_1.utilities.WeekStatisticsData
import com.example.hardmad2024_1.utilities.WeekRecyclerAdapter

class WeekStatisticsFragment: Fragment(R.layout.statistics_week) {
    private lateinit var binding:StatisticsWeekBinding
    private var data:Array<WeekStatisticsData> = arrayOf<WeekStatisticsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelableArray(BUNDLE_KEY)?.let {
            data = it as Array<WeekStatisticsData>
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsWeekBinding.bind(view)

        val recyclerView = binding.recordsContainer
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WeekRecyclerAdapter(
            requireContext(),
            data
        )
    }

    companion object{
        private const val BUNDLE_KEY = "WEEK_FRAGMENT_DATA"

        fun createNewInstance(weekStatisticsData: Array<WeekStatisticsData>):WeekStatisticsFragment{
            return WeekStatisticsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(BUNDLE_KEY, weekStatisticsData)
                }
            }
        }
    }
}