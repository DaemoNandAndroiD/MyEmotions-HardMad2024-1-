package com.example.hardmad2024_1

import android.os.Bundle
import android.text.TextPaint
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsOftenBinding
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

        repeat(data.size){
            addNewStatElement(
                data[it].emoteText,
                data[it].percent,
                data[it].emotionCount,
                data[it].icon,
                data[it].drawable
            )
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

    private fun addNewStatElement(emoteText:String, weight:Float, emotionsCount:Int, emotionDrawable:Int, layoutDrawable: Int){
        val statElement = layoutInflater.inflate(R.layout.statistics_often_element, binding.emotionsContainer, false)
        statElement.findViewById<TextView>(R.id.emoteText).text = emoteText
        statElement.findViewById<ImageView>(R.id.icon).setImageResource(emotionDrawable)

        val lp = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
        lp.weight = weight

        statElement.findViewById<TextView>(R.id.emotion_count).text = emotionsCount.toString()

        statElement.findViewById<LinearLayout>(R.id.stat_layout).layoutParams = lp
        statElement.findViewById<LinearLayout>(R.id.stat_layout).minimumWidth = calcMinWidth()
        statElement.findViewById<LinearLayout>(R.id.stat_layout).background = resources.getDrawable(layoutDrawable)
        binding.emotionsContainer.addView(statElement)
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