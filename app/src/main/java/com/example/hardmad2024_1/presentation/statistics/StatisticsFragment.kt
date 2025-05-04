package com.example.hardmad2024_1.presentation.statistics

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.StatisticsFragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.statistics_fragment) {
    private lateinit var binding: StatisticsFragmentBinding
    private val tabsConst = 56

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsFragmentBinding.bind(view)

        binding.horizontalTabLayout.setPadding(
            0, getStatusBarHeight(), 0, 0
        )

        val initialPosition = tabsConst-4

        val adapter = HorizontalViewPagerAdapter(this, tabCount = tabsConst, initialPosition = initialPosition)
        binding.viewPagerHorizontal.adapter = adapter

        TabLayoutMediator(
            binding.horizontalTabLayout, binding.viewPagerHorizontal
        ) { tab: TabLayout.Tab, i: Int ->
            tab.text = getWeeks(i - initialPosition)
        }.attach()

        binding.viewPagerHorizontal.post {
            binding.viewPagerHorizontal.setCurrentItem(initialPosition, false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getStatusBarHeight(): Int {
        val windowInsets: WindowInsets = requireActivity().window.decorView.rootWindowInsets
        return windowInsets.getInsets(WindowInsets.Type.statusBars()).top
    }

    private fun getWeeks(offset: Int): String {
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.WEEK_OF_YEAR, offset)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val start = calendar.time

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val end = calendar.time

        val dateFormat = SimpleDateFormat("d MMM", Locale.getDefault())
        val startMonth = SimpleDateFormat("MMM", Locale.getDefault()).format(start)
        val endMonth = SimpleDateFormat("MMM", Locale.getDefault()).format(end)
        val shortDateFormat = SimpleDateFormat("d", Locale.getDefault())

        return if (startMonth == endMonth) {
            "${shortDateFormat.format(start)} - ${dateFormat.format(end)}"
        } else {
            "${dateFormat.format(start)} - ${dateFormat.format(end)}"
        }
    }

    fun getWeekDates(offset: Int): Pair<Date, Date> {
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.WEEK_OF_YEAR, offset)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val start = calendar.time

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        val end = calendar.time

        return Pair(start, end)
    }
}