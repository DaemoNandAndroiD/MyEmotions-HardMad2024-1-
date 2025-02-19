package com.example.hardmad2024_1

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.hardmad2024_1.databinding.WelcomeActivityBinding


class WelcomeActivity : ComponentActivity() {
    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startGradientAnimation()

        binding.googleButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun startGradientAnimation() {
        val view = binding.gradientView

        val animatorX = ObjectAnimator.ofFloat(view, "translationX", -100f, 100f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            interpolator = LinearInterpolator()
        }

        val animatorY = ObjectAnimator.ofFloat(view, "translationY", -100f, 100f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            interpolator = LinearInterpolator()
        }

        AnimatorSet().apply {
            playTogether(animatorX, animatorY)
            start()
        }
    }
}