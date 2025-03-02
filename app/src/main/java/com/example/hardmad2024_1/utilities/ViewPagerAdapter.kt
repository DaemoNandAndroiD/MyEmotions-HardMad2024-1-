package com.example.hardmad2024_1.utilities

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hardmad2024_1.DuringDayStatisticsFragment
import com.example.hardmad2024_1.GeneralStatisticsFragment
import com.example.hardmad2024_1.OftenStatisticsFragment
import com.example.hardmad2024_1.WeekStatisticsFragment

class ViewPagerAdapter(fragment: Fragment, private val data: VerticalFragmentData) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GeneralStatisticsFragment.createNewInstance(data.generalData)
            1 -> WeekStatisticsFragment.createNewInstance(data.weekStatisticsData)
            2-> OftenStatisticsFragment.createNewInstance(data.oftenStatisticsData)
            3-> DuringDayStatisticsFragment.createNewInstance(data.duringDayStatisticsData)
            else -> DuringDayStatisticsFragment.createNewInstance(data.duringDayStatisticsData)
        }
    }
}
