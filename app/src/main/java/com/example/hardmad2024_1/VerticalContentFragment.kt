package com.example.hardmad2024_1

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.VerticalContentFragmentBinding
import com.example.hardmad2024_1.utilities.VerticalFragmentData
import com.example.hardmad2024_1.utilities.ViewPagerAdapter
import io.secf4ult.verticaltablayout.VerticalTabLayout
import io.secf4ult.verticaltablayout.VerticalTabLayoutMediator

class VerticalContentFragment: Fragment(R.layout.vertical_content_fragment) {
    private lateinit var binding:VerticalContentFragmentBinding
    private var data:VerticalFragmentData = VerticalFragmentData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getParcelable<VerticalFragmentData>(BUNDLE_KEY)?.let{
            data = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = VerticalContentFragmentBinding.bind(view)

        val adapter = ViewPagerAdapter(this, data)
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

    companion object{
        private const val BUNDLE_KEY = "FRAGMENT_DATA"

        fun createNewInstance(verticalFragmentData: VerticalFragmentData):VerticalContentFragment{
            return VerticalContentFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BUNDLE_KEY, verticalFragmentData)
                }
            }
        }
    }
}