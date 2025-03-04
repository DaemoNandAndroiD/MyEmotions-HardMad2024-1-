package com.example.hardmad2024_1.presentation.util.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.presentation.util.classes.OftenStatisticsData

class OftenElementsAdapter(private val oftenStatisticsData: List<OftenStatisticsData>, private val resources:Resources):RecyclerView.Adapter<OftenElementsAdapter.OftenElementViewHolder>() {
    class OftenElementViewHolder(view: View): ViewHolder(view){
        val iconView = view.findViewById<ImageView>(R.id.icon)
        val emoteText = view.findViewById<TextView>(R.id.emoteText)
        val statisticLayout = view.findViewById<LinearLayout>(R.id.stat_layout)
        val emotionCount = view.findViewById<TextView>(R.id.emotion_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OftenElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.statistics_often_element, parent, false)
        return OftenElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return oftenStatisticsData.size
    }

    override fun onBindViewHolder(holder: OftenElementViewHolder, position: Int) {
        holder.iconView.setImageResource(oftenStatisticsData[position].icon)
        holder.emoteText.setText(oftenStatisticsData[position].emoteText)

        val lp = LinearLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT)
        lp.weight = oftenStatisticsData[position].percent

        holder.statisticLayout.layoutParams = lp
        holder.statisticLayout.background = resources.getDrawable(oftenStatisticsData[position].drawable)

        holder.emotionCount.text = oftenStatisticsData[position].emotionCount.toString()
    }
}