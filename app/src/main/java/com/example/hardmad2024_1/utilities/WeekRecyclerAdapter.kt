package com.example.hardmad2024_1.utilities

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hardmad2024_1.R
import com.google.android.flexbox.FlexboxLayout

class WeekRecyclerAdapter(val context: Context, private val elements:Array<WeekStatisticsData>) :RecyclerView.Adapter<WeekRecyclerAdapter.WeekViewHolder>() {

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

        repeat(elements[position].emotionsTexts.size){
            holder.emotionsContainer.addView(createTextView(elements[position].emotionsTexts[it]))
        }

        if(elements[position].icons.isNotEmpty()){
            holder.placeholder.visibility = View.GONE
        }

        repeat(elements[position].icons.size){
            holder.iconContainer.addView(createImageView(elements[position].icons[it]))
        }
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
            40.toPx(context),
            40.toPx(context)
        )

        layoutParameters.setMargins(0,0, 4.toPx(context), 4.toPx(context))

        return ImageView(context).apply {
            layoutParams = layoutParameters
            setImageResource(drawable)
        }
    }
}