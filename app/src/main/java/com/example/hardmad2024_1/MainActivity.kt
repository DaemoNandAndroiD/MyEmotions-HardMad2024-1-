package com.example.hardmad2024_1

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.RadialGradient
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import com.example.hardmad2024_1.databinding.WelcomeActivityBinding


class WelcomeActivity : ComponentActivity() {
    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startGradientAnimation()
    }

    private fun startGradientAnimation() {
        val view = binding.gradientView

        val animatorX = ObjectAnimator.ofFloat(view, "translationX", -100f, 100f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            interpolator = LinearInterpolator()
        }

        val animatorY = ObjectAnimator.ofFloat(view, "translationY", -100f, 100f).apply {
            duration = 4000
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