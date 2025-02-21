package com.example.hardmad2024_1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsWeekBinding

class WeekStatisticsFragment: Fragment(R.layout.statistics_week) {
    private lateinit var binding:StatisticsWeekBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsWeekBinding.bind(view)
    }
}