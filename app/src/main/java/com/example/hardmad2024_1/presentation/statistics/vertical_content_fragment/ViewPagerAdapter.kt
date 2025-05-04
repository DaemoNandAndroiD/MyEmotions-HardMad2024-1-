package com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hardmad2024_1.presentation.statistics.during_day_statistics.DuringDayStatisticsFragment
import com.example.hardmad2024_1.presentation.statistics.general_statistics.GeneralStatisticsFragment
import com.example.hardmad2024_1.presentation.statistics.often_statistics.OftenStatisticsFragment
import com.example.hardmad2024_1.presentation.statistics.week_statistics.WeekStatisticsFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GeneralStatisticsFragment()
            1 -> WeekStatisticsFragment()
            2-> OftenStatisticsFragment()
            3-> DuringDayStatisticsFragment()
            else -> DuringDayStatisticsFragment()
        }
    }
}
