package com.example.hardmad2024_1.presentation.util.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.presentation.util.classes.JournalFragmentData

class JournalRecordsAdapter(private val resources:Resources, private val journalFragmentData: List<JournalFragmentData>, private val onClick:(JournalFragmentData)->Unit) : RecyclerView.Adapter<JournalRecordsAdapter.RecordsViewHolder>() {
    class RecordsViewHolder(view: View):ViewHolder(view){
        val parentView = view.findViewById<ConstraintLayout>(R.id.card)
        val dateText = view.findViewById<TextView>(R.id.date)
        val emotionText = view.findViewById<TextView>(R.id.emotion_type)
        val iconView = view.findViewById<ImageView>(R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.emotion_card, parent, false)
        return RecordsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return journalFragmentData.size
    }

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {
        holder.parentView.background = resources.getDrawable(journalFragmentData[position].backgroundDrawable)
        holder.dateText.text = journalFragmentData[position].date
        holder.emotionText.apply{
            text = journalFragmentData[position].emoteText
            setTextColor(resources.getColor(journalFragmentData[position].textColor))
        }
        holder.iconView.setImageResource(journalFragmentData[position].icon)

        holder.parentView.setOnClickListener {
            onClick(journalFragmentData[position])
        }
    }
}