package com.example.hardmad2024_1.presentation.activities

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.hardmad2024_1.databinding.WelcomeActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.cos
import kotlin.math.sin


@AndroidEntryPoint
class WelcomeActivity : ComponentActivity() {
    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        binding.googleButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val circles =
            listOf(binding.greenView, binding.blueView, binding.yellowView, binding.redView)
        val angles = listOf(180f, 270f, 0f, 90f)

        repeat(circles.size) {
            circles[it].post {
                val lp = ConstraintLayout.LayoutParams(
                    binding.constraintLayout.width * 2, binding.constraintLayout.height * 2
                )

                lp.setMargins(
                    -binding.constraintLayout.width,
                    -binding.constraintLayout.height,
                    -binding.constraintLayout.width,
                    -binding.constraintLayout.height
                )

                circles[it].layoutParams = lp
                startCircleAnimation(circles[it], angles[it])
            }
        }
    }

    //https://stackoverflow.com/questions/2912779/how-to-calculate-a-point-with-an-given-center-angle-and-radius
    //круги двигаются по кругу вписанному в экран
    private fun startCircleAnimation(view: View, start: Float) {
        ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 7000

            addUpdateListener { animator ->
                val value = animator.animatedValue as Float
                val angle = if (start + value > 360f) start + value - 360f else start + value
                //cos*radius + centerCircle
                //центр смещен за экран
                view.x =
                    (cos(Math.toRadians(angle.toDouble())) * binding.constraintLayout.width / 2 + -binding.constraintLayout.width / 2).toFloat()
                view.y =
                    (sin(Math.toRadians(angle.toDouble())) * binding.constraintLayout.width / 2 + -binding.constraintLayout.height / 2).toFloat()
            }

            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            start()
        }
    }

    /*private fun startGradientAnimation() {
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

    private fun startGradientAnimation() {
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