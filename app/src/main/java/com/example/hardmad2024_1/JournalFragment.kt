package com.example.hardmad2024_1

import android.graphics.Color
import android.graphics.RadialGradient
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
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

        val card = layoutInflater.inflate(R.layout.emotion_card, binding.emotionsList)
        val card1 = layoutInflater.inflate(R.layout.emotion_card, binding.emotionsList)
    }
}