package com.example.hardmad2024_1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsGeneralBinding

class GeneralStatisticsFragment:Fragment(R.layout.statistics_general) {
    private lateinit var binding:StatisticsGeneralBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsGeneralBinding.bind(view)
    }
}