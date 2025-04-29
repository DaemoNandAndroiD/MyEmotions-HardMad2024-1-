package com.example.hardmad2024_1.presentation.util.adapters

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.domain.models.EmotionModel

class EmotionCirclesAdapter(
    val resources: Resources,
    private var items: MutableList<EmotionModel> = mutableListOf(),
    val onItemClick: (EmotionModel) -> Unit
) : RecyclerView.Adapter<EmotionCirclesAdapter.EmotionViewHolder>() {
    private var previousView: View? = null
    private var previousViewIndex: Int? = 0

    class EmotionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val parent = view
        val backgroundView: View = view.findViewById(R.id.viewBackground)
        val text: TextView = view.findViewById(R.id.emotionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.circle_item, parent, false)
        return EmotionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: EmotionViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.text.text = resources.getString(items[position].name)
        holder.backgroundView.backgroundTintList = when (items[position].color) {
            EmotionColor.BLUE -> ResourcesCompat.getColorStateList(
                resources,
                R.color.blue_text,
                null
            )

            EmotionColor.GREEN -> ResourcesCompat.getColorStateList(
                resources,
                R.color.green_text,
                null
            )

            EmotionColor.RED -> ResourcesCompat.getColorStateList(resources, R.color.red_text, null)
            EmotionColor.YELLOW -> ResourcesCompat.getColorStateList(
                resources,
                R.color.yellow_text,
                null
            )
        }

        holder.parent.setOnClickListener {
            if (previousView != holder.parent) {
                previousView?.let { animateScale(it, 1f) }
                previousViewIndex?.let { shiftElements(it, holder, 0f) }

                animateScale(holder.parent, 1.3f)
                shiftElements(position, holder, 40f)

                onItemClick(items[position])

                previousView = holder.parent
                previousViewIndex = position
            }
        }
    }

    fun loadNewList(newItems: List<EmotionModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun shiftElements(position: Int, holder: EmotionViewHolder, translation: Float) {
        val row = position / 4
        val col = position % 4

        val recyclerView = holder.parent.parent as RecyclerView

        var index = position

        while (index + 4 <= 15) {
            index += 4
            recyclerView.findViewHolderForAdapterPosition(index)?.itemView?.let {
                animateTranslation(
                    it, translation, false
                )
            }
        }

        index = position

        while (index - 4 >= 0) {
            index -= 4
            recyclerView.findViewHolderForAdapterPosition(index)?.itemView?.let {
                animateTranslation(
                    it, -translation, false
                )
            }
        }

        index = position

        while ((index - 1) / 4 == position / 4) {
            index--
            recyclerView.findViewHolderForAdapterPosition(index)?.itemView?.let {
                animateTranslation(
                    it, -translation, true
                )
            }
        }

        index = position

        while ((index + 1) / 4 == position / 4) {
            index++
            recyclerView.findViewHolderForAdapterPosition(index)?.itemView?.let {
                animateTranslation(
                    it, translation, true
                )
            }
        }
    }
}

fun animateTranslation(view: View, translation: Float, isTranslationX: Boolean) {
    if (isTranslationX) {
        ValueAnimator.ofFloat(view.translationX, translation).apply {
            duration = 200
            addUpdateListener { animator ->
                val value = animator.animatedValue as Float

                view.translationX = value
            }
            start()
        }
    } else {
        ValueAnimator.ofFloat(view.translationY, translation).apply {
            duration = 200
            addUpdateListener { animator ->
                val value = animator.animatedValue as Float

                view.translationY = value
            }
            start()
        }
    }
}

fun animateScale(view: View, scale: Float) {
    ValueAnimator.ofFloat(view.scaleX, scale).apply {
        duration = 200
        addUpdateListener { animator ->
            val value = animator.animatedValue as Float
            view.scaleX = value
            view.scaleY = value
        }
        start()
    }
}
