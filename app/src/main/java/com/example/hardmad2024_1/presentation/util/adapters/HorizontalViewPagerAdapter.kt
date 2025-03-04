package com.example.hardmad2024_1.presentation.util.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hardmad2024_1.presentation.fragments.VerticalContentFragment
import com.example.hardmad2024_1.presentation.util.classes.VerticalFragmentData

class HorizontalViewPagerAdapter(fragment: Fragment, private val fragmentData:List<VerticalFragmentData>, private val tabCount:Int) :FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): VerticalContentFragment {
        return VerticalContentFragment.createNewInstance(fragmentData[position])
    }
}