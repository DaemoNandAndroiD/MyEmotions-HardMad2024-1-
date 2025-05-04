package com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.VerticalContentFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.secf4ult.verticaltablayout.VerticalTabLayout
import io.secf4ult.verticaltablayout.VerticalTabLayoutMediator
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class VerticalContentFragment : Fragment(R.layout.vertical_content_fragment) {
    private lateinit var binding: VerticalContentFragmentBinding
    private lateinit var datePair: Pair<Date, Date>
    private val viewModel by viewModels<VerticalStatisticsViewModel>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val serializable = arguments?.getSerializable(BUNDLE_KEY, Pair::class.java)

        if (serializable?.first is Date && serializable.second is Date) {
            datePair = serializable as Pair<Date, Date>
        } else {
            val today = Calendar.getInstance().time
            datePair = today to today
        }

        viewModel.getData(datePair.first, datePair.second)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = VerticalContentFragmentBinding.bind(view)

        val adapter = ViewPagerAdapter(this)
        binding.viewPagerVertical.adapter = adapter

        binding.viewPagerVertical.offscreenPageLimit = 1

        binding.viewPagerVertical.setPageTransformer { page, position ->
            if (position >= 0) {
                page.translationY = -page.height * 0.1f * position

                val alpha = 1 - (0.4f * position)
                page.alpha = alpha
            }
        }

        val verticalTabLayout: VerticalTabLayout = binding.verticalTabLayout

        VerticalTabLayoutMediator(
            verticalTabLayout,
            binding.viewPagerVertical,
            (VerticalTabLayoutMediator.TabConfigurationStrategy { tab: VerticalTabLayout.Tab, _: Int ->
                tab.setCustomView(ImageView(context).apply {
                    setImageResource(R.drawable.rounded_tab_indicator)
                })
            })
        ).attach()
    }

    companion object {
        private const val BUNDLE_KEY = "FRAGMENT_DATA"

        fun createNewInstance(start: Date, end: Date): VerticalContentFragment {
            return VerticalContentFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(BUNDLE_KEY, start to end)
                }
            }
        }
    }
}