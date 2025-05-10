package com.example.hardmad2024_1.presentation.statistics

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment.VerticalContentFragment

class HorizontalViewPagerAdapter(
    private val fragment: Fragment,
    private val tabCount: Int,
    private val initialPosition: Int
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): VerticalContentFragment {
        val statisticsFragment = fragment as StatisticsFragment
        val (start, end) = statisticsFragment.getWeekDates(position - initialPosition)

        return VerticalContentFragment.createNewInstance(start, end)
    }
}