package com.example.hardmad2024_1.presentation.statistics.during_day_statistics

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.StatisticsDuringDayBinding
import com.example.hardmad2024_1.domain.models.DuringDayStatisticsModel
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.domain.models.TimeInterval
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment.VerticalStatisticsViewModel
import com.example.hardmad2024_1.presentation.util.extensions.toPx
import kotlinx.coroutines.launch

class DuringDayStatisticsFragment : Fragment(R.layout.statistics_during_day) {
    private lateinit var binding: StatisticsDuringDayBinding
    private val viewModel by viewModels<VerticalStatisticsViewModel>({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsDuringDayBinding.bind(view)

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recordState.collect { state ->
                    when (state) {
                        is StateHandler.Error -> Unit
                        StateHandler.Initial -> Unit
                        StateHandler.Loading -> {
                            binding.progressBar.visibility = VISIBLE
                            binding.content.visibility = GONE
                        }
                        is StateHandler.Success -> {
                            generalSetup(state.data.duringDayData)
                            binding.progressBar.visibility = GONE
                            binding.content.visibility = VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun generalSetup(data: List<DuringDayStatisticsModel>) {
        data.forEach { model ->
            val (view, layout) = when (model.timeInterval) {
                TimeInterval.EARLY_MORNING -> binding.earlyMorningCount to binding.earlyMorningLayout
                TimeInterval.MORNING -> binding.morningCount to binding.morningLayout
                TimeInterval.AFTERNOON -> binding.afternoonCount to binding.afternoonLayout
                TimeInterval.EVENING -> binding.eveningCount to binding.eveningLayout
                TimeInterval.LATE_EVENING -> binding.lateEveningCount to binding.lateEveningLayout
                null -> binding.lateEveningCount to binding.lateEveningLayout
            }

            setupStatistics(view, model.amount, layout, model.percentage, model.colors)
        }
    }

    private fun setupStatistics(
        textView: TextView,
        amount: Int,
        layout: LinearLayout,
        weights: List<Float>,
        drawables: List<EmotionColor>
    ) {
        textView.text = amount.toString()

        if (weights.isNotEmpty()) {
            layout.removeViewAt(0)
        }

        repeat(weights.size) {
            val view = createStatView(weights[it], drawables[it])
            view.addView(createTextView(weights[it]))
            layout.addView(view, 0)
        }
    }

    private fun createStatView(weight: Float, color: EmotionColor): LinearLayout {
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        lp.weight = weight
        lp.setMargins(0, 0, 0, 4.toPx(requireContext()))

        return LinearLayout(requireContext()).apply {
            layoutParams = lp
            background = ResourcesCompat.getDrawable(
                resources, when (color) {
                    EmotionColor.BLUE -> R.drawable.blue_vertical_stat
                    EmotionColor.GREEN -> R.drawable.green_vertical_stat
                    EmotionColor.RED -> R.drawable.red_vertical_stat
                    EmotionColor.YELLOW -> R.drawable.yellow_vertical_stat
                }, null
            )
            gravity = CENTER_HORIZONTAL
        }
    }

    private fun createTextView(text: Float): TextView {
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