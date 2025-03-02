package com.example.hardmad2024_1

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.hardmad2024_1.databinding.WelcomeActivityBinding


class WelcomeActivity : ComponentActivity() {
    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        startGradientAnimation()

        binding.googleButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun startGradientAnimation() {
        val view = binding.gradientView

        val animatorX = ObjectAnimator.ofFloat(view, "translationX", -100f, 100f).apply {
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            interpolator = LinearInterpolator()
        }

        val animatorY = ObjectAnimator.ofFloat(view, "translationY", -100f, 100f).apply {
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            interpolator = LinearInterpolator()
        }

        AnimatorSet().apply {
            playTogether(animatorX, animatorY)
            start()
        }
    }

    /*private fun startGradientAnimation() {
        animateCorner(binding.greenView, 0f, 0f, 100f, 100f)
        animateCorner(binding.blueView, 100f, 0f, -100f, 100f)
        animateCorner(binding.redView, 100f, 100f, -100f, -100f)
        animateCorner(binding.yellowView, -100f, 100f, 100f, -100f)
    }

    private fun animateCorner(view: View, startX: Float, startY: Float, endX: Float, endY: Float) {
        val animatorX = ObjectAnimator.ofFloat(view, "translationX", startX, endX).apply {
            duration = 5000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            interpolator = LinearInterpolator()
        }

        val animatorY = ObjectAnimator.ofFloat(view, "translationY", startY, endY).apply {
            duration = 5000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            interpolator = LinearInterpolator()
        }

        AnimatorSet().apply {
            playTogether(animatorX, animatorY)
            start()
        }
    }*/
}