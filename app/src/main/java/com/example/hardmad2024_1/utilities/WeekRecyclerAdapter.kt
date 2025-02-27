package com.example.hardmad2024_1.utilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hardmad2024_1.R
import com.google.android.flexbox.FlexboxLayout

class WeekRecyclerAdapter(val elements:List<DayOfWeek>) :RecyclerView.Adapter<WeekRecyclerAdapter.WeekViewHolder>() {

    class WeekViewHolder(view: View):RecyclerView.ViewHolder(view){
        val dayOfWeekText = view.findViewById<TextView>(R.id.day_of_week)
        val dateText = view.findViewById<TextView>(R.id.date)
        val emotionsContainer = view.findViewById<LinearLayout>(R.id.emotions_container)
        val iconContainer = view.findViewById<FlexboxLayout>(R.id.icon_container)
        val placeholder = view.findViewById<ImageView>(R.id.placeholder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_of_week_stat, parent, false)

        return WeekViewHolder(view)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.dateText.text = elements[position].date
        holder.dayOfWeekText.text = elements[position].dayOfWeek

        repeat(elements[position].emotionsTextViews.size){
            holder.emotionsContainer.addView(elements[position].emotionsTextViews[it])
        }

        if(elements[position].icons.isNotEmpty()){
            holder.placeholder.visibility = View.GONE
        }

        repeat(elements[position].icons.size){
            holder.iconContainer.addView(elements[position].icons[it])
        }
    }
}