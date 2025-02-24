package com.example.hardmad2024_1

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsOftenBinding

class OftenStatisticsFragment:Fragment(R.layout.statistics_often) {
    private lateinit var binding:StatisticsOftenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = StatisticsOftenBinding.bind(view)

        addNewStatElement("Спокойствие", 1f,4, R.drawable.ic_green_emote, R.drawable.green_often_stat)
        addNewStatElement("Продуктивность", 0.75f, 3, R.drawable.ic_yellow_emote, R.drawable.yellow_often_stat)
        addNewStatElement("Счастье", 0.5f, 2, R.drawable.ic_yellow_emote_2, R.drawable.yellow_often_stat)
        addNewStatElement("Выгорание", 1f, 4, R.drawable.ic_blue_emote, R.drawable.blue_often_stat)
        addNewStatElement("Усталость", 0.25f, 1, R.drawable.ic_blue_emote_2, R.drawable.blue_often_stat)

    }

    private fun addNewStatElement(emoteText:String, weight:Float, emotionsCount:Int, emotionDrawable:Int, layoutDrawable: Int){
        var statElement = layoutInflater.inflate(R.layout.statistics_often_element, binding.emotionsContainer, false)
        statElement.findViewById<TextView>(R.id.emoteText).text = emoteText
        statElement.findViewById<ImageView>(R.id.icon).setImageResource(emotionDrawable)

        val lp = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
        lp.weight = weight

        statElement.findViewById<TextView>(R.id.emotion_count).text = emotionsCount.toString()

        statElement.findViewById<LinearLayout>(R.id.stat_layout).layoutParams = lp
        statElement.findViewById<LinearLayout>(R.id.stat_layout).background = resources.getDrawable(layoutDrawable)
        binding.emotionsContainer.addView(statElement)
    }
}