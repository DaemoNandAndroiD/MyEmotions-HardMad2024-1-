package com.example.hardmad2024_1.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.StatisticsGeneralBinding
import com.example.hardmad2024_1.presentation.util.classes.GeneralStatisticsData
import kotlin.math.sqrt

class GeneralStatisticsFragment:Fragment(R.layout.statistics_general) {
    private lateinit var binding:StatisticsGeneralBinding
    private var data: GeneralStatisticsData = GeneralStatisticsData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<GeneralStatisticsData>(BUNDLE_KEY).let {
            if (it != null){
                data = it
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsGeneralBinding.bind(view)
        binding.recordsCount.text = resources.getQuantityString(R.plurals.records,data.recordsCount,data.recordsCount)
        setupStat(data.percentagesList)
    }

    companion object{
        private const val BUNDLE_KEY = "GENERAL_FRAGMENT_DATA"

        fun createNewInstance(generalStatisticsData: GeneralStatisticsData): GeneralStatisticsFragment {
            return GeneralStatisticsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BUNDLE_KEY, generalStatisticsData)
                }
            }
        }
    }

    private fun setupStat(percentagesArgument: List<Float>){
        val percentages = percentagesArgument.sortedDescending()

        var width:Float = 0f
        var height:Float = 0f

        binding.circleContainer.post {
            width = binding.circleContainer.width.toFloat()
            height = binding.circleContainer.height.toFloat()

            val radius = calculateCircles(
                width,
                height,
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
            val sizePx = (radius[0] * 2).toInt()

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
            val sizePx = (radius[1] * 2).toInt()

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
            val sizePx = (radius[2] * 2).toInt()

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
            val sizePx = (radius[3] * 2).toInt()

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