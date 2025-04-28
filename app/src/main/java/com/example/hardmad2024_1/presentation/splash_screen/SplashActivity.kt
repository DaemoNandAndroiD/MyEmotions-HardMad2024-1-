package com.example.hardmad2024_1.presentation.splash_screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.viewModels
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.activities.MainActivity
import com.example.hardmad2024_1.presentation.welcome_screen.WelcomeActivity
import com.example.hardmad2024_1.presentation.welcome_screen.auth.GoogleAuthUiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : FragmentActivity() {
    @Inject lateinit var googleAuthUiClient: GoogleAuthUiClient
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.fingerPrintEnabledState.collect { state ->
                when (state) {
                    is StateHandler.Error -> Unit
                    StateHandler.Initial -> Unit
                    StateHandler.Loading -> Unit
                    is StateHandler.Success -> {
                        if (state.data) {
                            startBiometricAuth()
                        }
                        else{
                            checkAuth()
                        }
                    }
                }
            }
        }
    }

    private fun checkAuth() {
        if (googleAuthUiClient.getSignedInUser() != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        else{
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun startBiometricAuth() {
        val prompt = BiometricPrompt(
            this,
            mainExecutor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    checkAuth()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    vibrate()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    finishAffinity()
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Вход в приложение")
            .setNegativeButtonText("Отмена")
            .build()

        prompt.authenticate(promptInfo)
    }

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}