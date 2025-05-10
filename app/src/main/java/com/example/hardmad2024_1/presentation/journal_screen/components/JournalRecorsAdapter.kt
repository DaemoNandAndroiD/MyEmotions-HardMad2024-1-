package com.example.hardmad2024_1.presentation.journal_screen.components

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.presentation.util.extensions.toPx

class JournalRecordsAdapter(
    private val resources: Resources,
    private val context: Context,
    private var items: MutableList<RecordModel> = mutableListOf<RecordModel>(),
    private val onClick: (RecordModel) -> Unit
) : RecyclerView.Adapter<JournalRecordsAdapter.RecordsViewHolder>() {

    class RecordsViewHolder(view: View) : ViewHolder(view) {
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
        return items.size
    }

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {
        if (position - 1 >= 0
            && items[position - 1].date.split(',')[0] !=
            items[position].date.split(',')[0]
        ) {
            val lp = holder.parentView.layoutParams as ViewGroup.MarginLayoutParams
            lp.setMargins(24.toPx(context),16.toPx(context),24.toPx(context),0)
            holder.parentView.layoutParams = lp
        }

        holder.parentView.background = ResourcesCompat.getDrawable(
            resources, when (items[position].emotionColor) {
                EmotionColor.BLUE -> R.drawable.card_shape_blue
                EmotionColor.GREEN -> R.drawable.card_shape_green
                EmotionColor.RED -> R.drawable.card_shape_red
                EmotionColor.YELLOW -> R.drawable.card_shape_yellow
            }, null
        )


        holder.dateText.text = items[position].date
        holder.emotionText.apply {
            text = resources.getString(items[position].emotionName)
            setTextColor(
                ResourcesCompat.getColor(
                    resources, when (items[position].emotionColor) {
                        EmotionColor.BLUE -> R.color.blue_text
                        EmotionColor.GREEN -> R.color.green_text
                        EmotionColor.RED -> R.color.red_text
                        EmotionColor.YELLOW -> R.color.yellow_text
                    }, null
                )
            )
        }

        holder.iconView.setImageResource(items[position].icon)

        holder.parentView.setOnClickListener {
            onClick(items[position])
        }
    }

    fun loadNewList(newItems: List<RecordModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }
}