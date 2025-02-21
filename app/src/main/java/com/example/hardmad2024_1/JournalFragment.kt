package com.example.hardmad2024_1

import android.graphics.Color
import android.graphics.RadialGradient
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.JournalFragmentBinding


class JournalFragment:Fragment(R.layout.journal_fragment){
    private lateinit var binding: JournalFragmentBinding
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = JournalFragmentBinding.bind(view)

        binding.records.text = this.resources.getQuantityString(R.plurals.records, 5, 5)
        binding.perDay.text = this.resources.getQuantityString(R.plurals.records, 2, 2)
        binding.streak.text = this.resources.getQuantityString(R.plurals.days, 3, 3)

        val rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotation_gradient)
        binding.progressBar.animation = rotateAnimation

        initCard()
    }

    private fun initCard(){
        val card = layoutInflater.inflate(R.layout.emotion_card, binding.emotionsList, false)
        val card1 = layoutInflater.inflate(R.layout.emotion_card, binding.emotionsList, false)
        val card2 = layoutInflater.inflate(R.layout.emotion_card, binding.emotionsList, false)
        val card3 = layoutInflater.inflate(R.layout.emotion_card, binding.emotionsList, false)

        activity?.let {
            card.background = it.getDrawable(R.drawable.card_shape_blue)
            card.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_blue_emote)
            val emotionType = card.findViewById<TextView>(R.id.emotion_type)
            emotionType.apply {
                text = "выгорание"
                setTextColor(resources.getColor(R.color.blue_text))
            }

            card1.background = it.getDrawable(R.drawable.card_shape_green)
            card1.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_green_emote)
            val emotionType1 = card1.findViewById<TextView>(R.id.emotion_type)
            emotionType1.apply {
                text = "спокойствие"
                setTextColor(resources.getColor(R.color.green_text))
            }

            card2.background = it.getDrawable(R.drawable.card_shape_yellow)
            card2.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_yellow_emote)
            val emotionType2 = card2.findViewById<TextView>(R.id.emotion_type)
            emotionType2.apply {
                text = "продуктивность"
                setTextColor(resources.getColor(R.color.yellow_text))
            }

            card3.background = it.getDrawable(R.drawable.card_shape_red)
            card3.findViewById<ImageView>(R.id.icon).setImageResource(R.drawable.ic_red_emote)
            val emotionType3 = card3.findViewById<TextView>(R.id.emotion_type)
            emotionType3.apply {
                text = "беспокойство"
                setTextColor(resources.getColor(R.color.red_text))
            }
        }

        binding.emotionsList.addView(card)
        binding.emotionsList.addView(card1)
        binding.emotionsList.addView(card2)
        binding.emotionsList.addView(card3)
    }
}