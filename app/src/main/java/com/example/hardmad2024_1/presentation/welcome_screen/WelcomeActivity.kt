package com.example.hardmad2024_1.presentation.welcome_screen

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.hardmad2024_1.databinding.WelcomeActivityBinding
import com.example.hardmad2024_1.presentation.main_screen.MainActivity
import com.example.hardmad2024_1.presentation.welcome_screen.auth.GoogleAuthUiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.cos
import kotlin.math.sin


@AndroidEntryPoint
class WelcomeActivity : FragmentActivity() {
    private lateinit var binding: WelcomeActivityBinding
    private val viewModel by viewModels<WelcomeViewModel>()

    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupScreenDecorators()
        setupClickListeners()
        setupAnimation()
    }

    private fun setupScreenDecorators() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun setupClickListeners() {
        binding.googleButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            lifecycleScope.launch {
                val user = googleAuthUiClient.signIn()

                if (user != null) {
                    viewModel.addUser(user.first, user.second ?: "John Doe")
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun setupAnimation() {
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

    private fun startCircleAnimation(view: View, start: Float) {
        ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 7000

            addUpdateListener { animator ->
                val value = animator.animatedValue as Float
                val angle = if (start + value > 360f) start + value - 360f else start + value

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
}