package com.example.hardmad2024_1.presentation.statistics.general_statistics

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.StatisticsGeneralBinding
import com.example.hardmad2024_1.domain.models.CircleData
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.domain.models.GeneralStatisticsModel
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment.VerticalStatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import kotlin.math.sqrt

@AndroidEntryPoint
class GeneralStatisticsFragment:Fragment(R.layout.statistics_general) {
    private lateinit var binding:StatisticsGeneralBinding

    private val viewModel by viewModels<VerticalStatisticsViewModel>({requireParentFragment()})

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsGeneralBinding.bind(view)

        observeViewModel()
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.recordState.collect{state->
                    when(state){
                        is StateHandler.Error -> Unit
                        StateHandler.Initial -> Unit
                        StateHandler.Loading -> Unit
                        is StateHandler.Success -> setupStat(state.data.generalData)

                    }
                }
            }
        }
    }

    private fun setupStat(generalData : GeneralStatisticsModel){
        val percentages = generalData.circleDataList.sortedByDescending { it.percentage }

        var width:Float = 0f
        var height:Float = 0f

        binding.circleContainer.post {
            width = binding.circleContainer.width.toFloat()
            height = binding.circleContainer.height.toFloat()

            val radius = calculateCircles(
                width,
                height,
                percentages.map { it.percentage }
            )

            if(radius.size == 1){
                initOneCircle(radius, percentages)
            }
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

    fun initOneCircle(radius:List<Float>, percentages: List<CircleData>){
        binding.circle1.apply {
            val sizePx = (radius[0] * 2).toInt()

            layoutParams.apply {
                height = sizePx
                width = sizePx
            }

            requestLayout()
            background = when(percentages[0].color){
                EmotionColor.BLUE -> context.getDrawable(R.drawable.blue_circle_stat)
                EmotionColor.GREEN -> context.getDrawable(R.drawable.green_circle_stat)
                EmotionColor.RED -> context.getDrawable(R.drawable.red_circle_stat)
                EmotionColor.YELLOW -> context.getDrawable(R.drawable.yellow_circle_stat)
            }
        }

        binding.circleText1.text = percentages[0].percentage.toInt().toString()+"%"
    }

    fun initTwoCircle(radius:List<Float>, percentages: List<CircleData>){
        initOneCircle(radius,percentages)

        binding.circle2.apply {
            val sizePx = (radius[1] * 2).toInt()

            layoutParams.apply {
                height = sizePx
                width = sizePx
            }

            requestLayout()
            background = when(percentages[1].color){
                EmotionColor.BLUE -> context.getDrawable(R.drawable.blue_circle_stat)
                EmotionColor.GREEN -> context.getDrawable(R.drawable.green_circle_stat)
                EmotionColor.RED -> context.getDrawable(R.drawable.red_circle_stat)
                EmotionColor.YELLOW -> context.getDrawable(R.drawable.yellow_circle_stat)
            }
        }

        binding.circleText2.text = percentages[1].percentage.toInt().toString()+"%"
    }

    fun initThreeCircle(radius:List<Float>, percentages: List<CircleData>){
        initOneCircle(radius,percentages)
        initTwoCircle(radius,percentages)

        binding.circle3.apply {
            val sizePx = (radius[2] * 2).toInt()

            layoutParams.apply {
                height = sizePx
                width = sizePx
            }

            requestLayout()
            background = when(percentages[2].color){
                EmotionColor.BLUE -> context.getDrawable(R.drawable.blue_circle_stat)
                EmotionColor.GREEN -> context.getDrawable(R.drawable.green_circle_stat)
                EmotionColor.RED -> context.getDrawable(R.drawable.red_circle_stat)
                EmotionColor.YELLOW -> context.getDrawable(R.drawable.yellow_circle_stat)
            }
        }

        binding.circleText3.text = percentages[2].percentage.toInt().toString()+"%"
    }

    fun initFourCircle(radius:List<Float>, percentages: List<CircleData>){
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
            background = when(percentages[3].color){
                EmotionColor.BLUE -> context.getDrawable(R.drawable.blue_circle_stat)
                EmotionColor.GREEN -> context.getDrawable(R.drawable.green_circle_stat)
                EmotionColor.RED -> context.getDrawable(R.drawable.red_circle_stat)
                EmotionColor.YELLOW -> context.getDrawable(R.drawable.yellow_circle_stat)
            }
        }

        binding.circleText4.text = percentages[3].percentage.toInt().toString()+"%"
    }
}