package com.example.hardmad2024_1

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hardmad2024_1.databinding.StatisticsWeekBinding
import com.example.hardmad2024_1.utilities.DayOfWeek
import com.example.hardmad2024_1.utilities.WeekRecyclerAdapter
import com.example.hardmad2024_1.utilities.toPx

class WeekStatisticsFragment: Fragment(R.layout.statistics_week) {
    private lateinit var binding:StatisticsWeekBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsWeekBinding.bind(view)

        val recyclerView = binding.recordsContainer
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WeekRecyclerAdapter(
            listOf(
                DayOfWeek(
                    date = "18 февраля",
                    dayOfWeek = getString(R.string.first_day),
                    emotionsTextViews = listOf(
                        createTextView("Спокойствие"),
                        createTextView("Продуктивность"),
                        createTextView("Счастье"),
                        createTextView("Радость"),
                        ),
                    icons = listOf(
                        createImageView(R.drawable.ic_green_emote),
                        createImageView(R.drawable.ic_yellow_emote),
                        createImageView(R.drawable.ic_yellow_emote_2),
                        createImageView(R.drawable.ic_red_emote)
                    )
                ),
                DayOfWeek(
                    date = "19 февраля",
                    dayOfWeek = getString(R.string.second_day),
                    emotionsTextViews = listOf(
                        createTextView("Выгорание"),
                        createTextView("Усталость"),
                    ),
                    icons = listOf(
                        createImageView(R.drawable.ic_blue_emote),
                        createImageView(R.drawable.ic_blue_emote_2),
                    )
                ),
                DayOfWeek(
                    date = "20 февраля",
                    dayOfWeek = getString(R.string.third_day),
                    emotionsTextViews = listOf(),
                    icons = listOf()
                ),
                DayOfWeek(
                    date = "21 февраля",
                    dayOfWeek = getString(R.string.fourth_day),
                    emotionsTextViews = listOf(),
                    icons = listOf()
                ),
                DayOfWeek(
                    date = "22 февраля",
                    dayOfWeek = getString(R.string.fifth_day),
                    emotionsTextViews = listOf(),
                    icons = listOf()
                ),
                DayOfWeek(
                    date = "23 февраля",
                    dayOfWeek = getString(R.string.sixth_day),
                    emotionsTextViews = listOf(),
                    icons = listOf()
                ),
                DayOfWeek(
                    date = "24 февраля",
                    dayOfWeek = getString(R.string.seventh_day),
                    emotionsTextViews = listOf(),
                    icons = listOf()
                ),
            )
        )


        //setupDays()
    }

    private fun setupDays(){
        val viewList = mutableListOf<View>()

        repeat(7){
            val day = layoutInflater.inflate(R.layout.day_of_week_stat, binding.recordsContainer, false)
            viewList.add(day)
        }

        setupDay(viewList[0], getString(R.string.first_day),
            listOf("Спокойствие", "Продуктивность", "Счастье"),
            listOf(R.drawable.ic_green_emote,R.drawable.ic_yellow_emote,R.drawable.ic_yellow_emote_2)
        )
        setupDay(viewList[1], getString(R.string.second_day),
            listOf("Выгорание", "Усталость"),
            listOf(R.drawable.ic_blue_emote,R.drawable.ic_blue_emote_2)
        )
        setupDay(viewList[2], getString(R.string.third_day))
        setupDay(viewList[3], getString(R.string.fourth_day))
        setupDay(viewList[4], getString(R.string.fifth_day))
        setupDay(viewList[5], getString(R.string.sixth_day))
        setupDay(viewList[6], getString(R.string.seventh_day))

    }

    private fun setupDay(view:View, day:String, emotions:List<String> = listOf(), icons:List<Int> = listOf()){
        view.findViewById<TextView>(R.id.day_of_week).text = day

        val emotionsContainer = view.findViewById<RecyclerView>(R.id.emotions_container)
        repeat(emotions.size){
            emotionsContainer.addView(createTextView(emotions[it]))
        }

        val iconsContainer = view.findViewById<LinearLayout>(R.id.icon_container)

        if(icons.isNotEmpty()){
            iconsContainer.findViewById<ImageView>(R.id.placeholder).visibility = View.GONE
        }
        repeat(icons.size){
            iconsContainer.addView(createImageView(icons[it]))
        }

        binding.recordsContainer.addView(view)
    }

    private fun createTextView(text:String):TextView{
        return TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setTextColor(resources.getColor(R.color.white))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
            typeface = resources.getFont(R.font.velasans_regular)
            setText(text)
        }
    }

    private fun createImageView(drawable:Int): ImageView {
        val layoutParameters = LinearLayout.LayoutParams(
            40.toPx(requireContext()),
            40.toPx(requireContext())
        )

        layoutParameters.setMargins(0,0, 4.toPx(requireContext()), 4.toPx(requireContext()))

        return ImageView(context).apply {
            layoutParams = layoutParameters
            setImageResource(drawable)
        }
    }
}