package com.example.hardmad2024_1.utilities

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.example.hardmad2024_1.R

class EmotionCirclesAdapter(val context: Context, val circles:List<CircleClass>, val title: TextView, val desc:TextView, val onClick:()->Unit) :RecyclerView.Adapter<EmotionCirclesAdapter.EmotionViewHolder>() {
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

fun animateSize(view: View, mode:Int, context: Context) {
    if (mode == 0){
        ValueAnimator.ofFloat(view.width.toFloat(), 112.toPx(context).toFloat()).apply {
            duration = 200
            addUpdateListener { animator ->
                val value = animator.animatedValue as Float

                val lp = view.layoutParams
                lp.width = value.toInt()
                lp.height = value.toInt()

                view.layoutParams = lp
            }
            start()
        }
    }
    else{
        ValueAnimator.ofFloat(view.width.toFloat(), 152.toPx(context).toFloat()).apply {
            duration = 200
            addUpdateListener { animator ->
                val value = animator.animatedValue as Float

                val lp = view.layoutParams
                lp.width = value.toInt()
                lp.height = value.toInt()

                view.layoutParams = lp
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
