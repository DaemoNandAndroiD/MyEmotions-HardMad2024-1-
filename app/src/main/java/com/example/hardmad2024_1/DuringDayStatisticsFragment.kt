package com.example.hardmad2024_1

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsDuringDayBinding
import com.example.hardmad2024_1.utilities.DuringDayStatisticsData
import com.example.hardmad2024_1.utilities.OftenStatisticsData
import com.example.hardmad2024_1.utilities.toPx

class DuringDayStatisticsFragment:Fragment(R.layout.statistics_during_day) {
    private lateinit var binding:StatisticsDuringDayBinding
    private var data:Array<DuringDayStatisticsData> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelableArray(BUNDLE_KEY).let {
            data = it as Array<DuringDayStatisticsData>
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsDuringDayBinding.bind(view)

        setupStatistics(
            binding.earlyMorningCount,
            data[0].amount,
            binding.earlyMorningLayout,
            data[0].percentages,
            data[0].drawables
        )

        setupStatistics(
            binding.morningCount,
            data[1].amount,
            binding.morningLayout,
            data[1].percentages,
            data[1].drawables
        )

        setupStatistics(
            binding.afternoonCount,
            data[2].amount,
            binding.afternoonLayout,
            data[2].percentages,
            data[2].drawables
        )

        setupStatistics(
            binding.eveningCount,
            data[3].amount,
            binding.eveningLayout,
            data[3].percentages,
            data[3].drawables
        )

        setupStatistics(
            binding.lateEveningCount,
            data[4].amount,
            binding.lateEveningLayout,
            data[4].percentages,
            data[4].drawables
        )
    }

    companion object{
        private const val BUNDLE_KEY = "DURING_DAY_FRAGMENT_DATA"

        fun createNewInstance(duringDayStatisticsData: Array<DuringDayStatisticsData>):DuringDayStatisticsFragment{
            return DuringDayStatisticsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(BUNDLE_KEY, duringDayStatisticsData)
                }
            }
        }
    }

    private fun setupStatistics(textView:TextView, amount:Int, layout:LinearLayout, weights:List<Int>, drawables:List<Int>){
        textView.text = amount.toString()

        val castedWeights = weights.map { item->
            item/amount.toFloat()
        }

        if (weights.isNotEmpty()){
            layout.removeViewAt(0)
        }

        repeat(weights.size){
            val view = createStatView(castedWeights[it], drawables[it])
            view.addView(createTextView(castedWeights[it]))
            layout.addView(view, 0)
        }
    }
    
    private fun createStatView(weight:Float, background:Int):LinearLayout{
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0
        )

        lp.weight = weight
        lp.setMargins(0,0,0,4.toPx(requireContext()))
        
        return LinearLayout(requireContext()).apply {
            layoutParams = lp
            setBackground(resources.getDrawable(background))
            gravity = CENTER_HORIZONTAL
        }
    }

    private fun createTextView(text:Float): TextView {
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        lp.gravity = CENTER_VERTICAL

        return TextView(requireContext()).apply {
            layoutParams = lp
            setText((text * 100f).toInt().toString() + "%")
            setTextColor(resources.getColor(R.color.black))
            typeface = resources.getFont(R.font.velasans_bold)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
            gravity = CENTER_VERTICAL
        }
    }
}