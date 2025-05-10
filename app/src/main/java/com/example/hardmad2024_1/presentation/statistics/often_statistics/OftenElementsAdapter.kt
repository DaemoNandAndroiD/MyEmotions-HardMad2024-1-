package com.example.hardmad2024_1.presentation.statistics.often_statistics

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.domain.models.OftenStatisticsModel

class OftenElementsAdapter(
    private val resources: Resources,
    private var oftenStatisticsData: List<OftenStatisticsModel> = mutableListOf()
) : RecyclerView.Adapter<OftenElementsAdapter.OftenElementViewHolder>() {
    class OftenElementViewHolder(view: View) : ViewHolder(view) {
        val iconView: ImageView = view.findViewById(R.id.icon)
        val emoteText: TextView = view.findViewById(R.id.emoteText)
        val statisticLayout: LinearLayout = view.findViewById(R.id.stat_layout)
        val emotionCount: TextView = view.findViewById(R.id.emotion_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OftenElementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.statistics_often_element, parent, false)
        return OftenElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return oftenStatisticsData.size
    }

    override fun onBindViewHolder(holder: OftenElementViewHolder, position: Int) {
        holder.iconView.setImageResource(oftenStatisticsData[position].icon)
        holder.emoteText.text = resources.getString(oftenStatisticsData[position].emotionText)

        val lp = LinearLayout.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.MATCH_PARENT
        )
        lp.weight = oftenStatisticsData[position].percent

        holder.statisticLayout.layoutParams = lp
        holder.statisticLayout.background = ResourcesCompat.getDrawable(resources, when(oftenStatisticsData[position].color){
            EmotionColor.BLUE -> R.drawable.blue_often_stat
            EmotionColor.GREEN -> R.drawable.green_often_stat
            EmotionColor.RED -> R.drawable.red_often_stat
            EmotionColor.YELLOW -> R.drawable.yellow_often_stat
        }, null)


        holder.emotionCount.text = oftenStatisticsData[position].amount.toString()
    }

    fun loadNewItems(newItems : List<OftenStatisticsModel>){
        oftenStatisticsData = newItems
        notifyDataSetChanged()
    }
}