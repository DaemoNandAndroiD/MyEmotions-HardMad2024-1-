package com.example.hardmad2024_1

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsGeneralBinding
import com.example.hardmad2024_1.utilities.toPx
import kotlin.math.sqrt

class GeneralStatisticsFragment:Fragment(R.layout.statistics_general) {
    private lateinit var binding:StatisticsGeneralBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsGeneralBinding.bind(view)

        setupStat()
    }

    private fun setupStat(){
        var percentages = listOf(50f, 20f, 30f)
        percentages = percentages.sortedDescending()

        var radius = calculateCircles(
            364f,
            430f,
            percentages
        )

        if (radius.size == 2){
            initTwoCircle(radius, percentages)

            binding.circle1.translationZ = 1f
            binding.circle2.translationZ = 0f
        }

        if (radius.size == 3){
            initThreeCircle(radius, percentages)

            binding.circle1.translationZ = 2f
            binding.circle2.translationZ = 1f
            binding.circle3.translationZ = 0f
        }
        if (radius.size == 4){
            initFourCircle(radius, percentages)

            binding.circle1.translationZ = 3f
            binding.circle2.translationZ = 2f
            binding.circle3.translationZ = 1f
            binding.circle4.translationZ = 0f
        }


    }

    private fun calculateCircles(containerWidth: Float, containerHeight: Float, percentages: List<Float>, alpha: Float = 0.7f): List<Float> {
        val totalCircleArea = alpha * containerWidth * containerHeight

        val circles = percentages.map { percentage ->
            val area = (percentage / 100f) * totalCircleArea
            val radius = sqrt(area / Math.PI).toFloat()
            radius
        }

        return circles
    }

    fun initOneCircle(radius:List<Float>, percentages: List<Float>){
        binding.circle1.apply {
            val sizePx = (radius[0] * 2).toInt().toPx(context)

            layoutParams.apply {
                height = sizePx
                width = sizePx
            }

            requestLayout()
            background = context.getDrawable(R.drawable.green_circle_stat)
        }

        binding.circleText1.text = percentages[0].toInt().toString()+"%"
    }

    fun initTwoCircle(radius:List<Float>, percentages: List<Float>){
        initOneCircle(radius,percentages)

        binding.circle2.apply {
            val sizePx = (radius[1] * 2).toInt().toPx(context)

            layoutParams.apply {
                height = sizePx
                width = sizePx
            }

            requestLayout()
            background = context.getDrawable(R.drawable.yellow_circle_stat)
        }

        binding.circleText2.text = percentages[1].toInt().toString()+"%"
    }

    fun initThreeCircle(radius:List<Float>, percentages: List<Float>){
        initOneCircle(radius,percentages)
        initTwoCircle(radius,percentages)

        binding.circle3.apply {
            val sizePx = (radius[2] * 2).toInt().toPx(context)

            layoutParams.apply {
                height = sizePx
                width = sizePx
            }

            requestLayout()
            background = context.getDrawable(R.drawable.red_circle_stat)
        }

        binding.circleText3.text = percentages[2].toInt().toString()+"%"
    }

    fun initFourCircle(radius:List<Float>, percentages: List<Float>){
        initOneCircle(radius,percentages)
        initTwoCircle(radius,percentages)
        initThreeCircle(radius,percentages)

        binding.circle4.apply {
            val sizePx = (radius[3] * 2).toInt().toPx(context)

            layoutParams.apply {
                height = sizePx
                width = sizePx
            }

            requestLayout()
            background = context.getDrawable(R.drawable.blue_circle_stat)
        }

        binding.circleText4.text = percentages[3].toInt().toString()+"%"
    }
}