package com.example.hardmad2024_1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsDuringDayBinding

class DuringDayStatisticsFragment:Fragment(R.layout.statistics_during_day) {
    private lateinit var binding:StatisticsDuringDayBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsDuringDayBinding.bind(view)
    }
}