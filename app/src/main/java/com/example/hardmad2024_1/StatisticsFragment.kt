package com.example.hardmad2024_1

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsFragmentBinding
import com.example.hardmad2024_1.utilities.ViewPagerAdapter
import io.secf4ult.verticaltablayout.VerticalTabLayout
import io.secf4ult.verticaltablayout.VerticalTabLayoutMediator


class StatisticsFragment: Fragment(R.layout.statistics_fragment) {
    private lateinit var binding:StatisticsFragmentBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsFragmentBinding.bind(view)

        binding.tabLayout.setPadding(
            0,getStatusBarHeight(),0,0
        )

        val adapter = ViewPagerAdapter(this)
        binding.viewPagerVertical.adapter = adapter

        val verticalTabLayout: VerticalTabLayout = binding.verticalTabLayout

        VerticalTabLayoutMediator(
            verticalTabLayout,
            binding.viewPagerVertical,
            (VerticalTabLayoutMediator.TabConfigurationStrategy { tab: VerticalTabLayout.Tab, position: Int ->
                tab.setCustomView(ImageView(context).apply {
                    setImageResource(R.drawable.rounded_tab_indicator)
                })
            })
        ).attach()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getStatusBarHeight():Int{
        val windowInsets: WindowInsets = requireActivity().window.decorView.rootWindowInsets
        return windowInsets.getInsets(WindowInsets.Type.statusBars()).top
    }
}