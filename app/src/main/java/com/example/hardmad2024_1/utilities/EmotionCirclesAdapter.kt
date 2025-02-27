package com.example.hardmad2024_1.utilities

import android.animation.ValueAnimator
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hardmad2024_1.R

class EmotionCirclesAdapter(val circles:List<CircleClass>, val title: TextView, val desc:TextView, val onClick:()->Unit) :RecyclerView.Adapter<EmotionCirclesAdapter.EmotionViewHolder>() {
    var previousView:View? = null

    class EmotionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val parent = view
        val backgroundView = view.findViewById<View>(R.id.viewBackground)
        val text: TextView = view.findViewById(R.id.emotionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.circle_item, parent, false)
        return EmotionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return circles.size
    }

    override fun onBindViewHolder(holder: EmotionViewHolder, position: Int) {
        holder.text.text = circles[position].text
        holder.backgroundView.backgroundTintList = circles[position].backgroundTint

        holder.parent.setOnClickListener {
            if (previousView != holder.parent) {
                previousView?.let { animateScale(it, 1f) }

                animateScale(holder.parent, 1.3f)
                onClick()

                title.text = circles[position].text
                title.setTextColor(circles[position].backgroundTint)

                desc.text = circles[position].hiddenDescription

                previousView = holder.parent
            }
        }
    }
}

class EmotionItemDecoration(private val scaleProvider: () -> Int?) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val selectedPosition = scaleProvider()

        val margin = if (position == selectedPosition) 60 else 20
        outRect.set(margin, margin, margin, margin)
    }
}

fun animateScale(view: View, scale: Float) {
    ValueAnimator.ofFloat(view.scaleX, scale).apply {
        duration = 100
        addUpdateListener { animator ->
            val value = animator.animatedValue as Float
            view.scaleX = value
            view.scaleY = value
        }
        start()
    }
}
