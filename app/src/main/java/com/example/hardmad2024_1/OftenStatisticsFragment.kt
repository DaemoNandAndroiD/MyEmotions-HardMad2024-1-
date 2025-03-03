package com.example.hardmad2024_1

import android.os.Bundle
import android.text.TextPaint
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardmad2024_1.databinding.StatisticsOftenBinding
import com.example.hardmad2024_1.utilities.OftenElementsAdapter
import com.example.hardmad2024_1.utilities.OftenStatisticsData

class OftenStatisticsFragment:Fragment(R.layout.statistics_often) {
    private lateinit var binding:StatisticsOftenBinding
    private var data:Array<OftenStatisticsData> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelableArray(BUNDLE_KEY).let {
            data = it as Array<OftenStatisticsData>
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = StatisticsOftenBinding.bind(view)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = OftenElementsAdapter(data.toList(), resources)

        binding.emotionsContainer.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }

    companion object{
        private const val BUNDLE_KEY = "OFTEN_FRAGMENT_DATA"

        fun createNewInstance(oftenStatisticsData: Array<OftenStatisticsData>):OftenStatisticsFragment{
            return OftenStatisticsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(BUNDLE_KEY, oftenStatisticsData)
                }
            }
        }
    }

    private fun calcMinWidth() :Int{
        val density = requireContext().resources.displayMetrics.density
        val dpToPx = { dp: Float -> dp * density }

        val leftRightPadding = dpToPx(16f)

        val textSizeSp = 16f
        val textPaint = TextPaint().apply {
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSizeSp, requireContext().resources.displayMetrics)
        }

        val textWidth = textPaint.measureText("AAA")
        val totalWidth = (textWidth + leftRightPadding * 2).toInt()
        return totalWidth
    }
}