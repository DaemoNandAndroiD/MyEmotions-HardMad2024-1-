package com.example.hardmad2024_1

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.presentation.welcome_screen.WelcomeActivity
import com.example.hardmad2024_1.screens.WelcomeScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeScreenTest:TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<WelcomeActivity>()

    @Test fun viewVisibilityTest(){
        WelcomeScreen{
            greetText{
                isDisplayed()
                isVisible()
                hasText(R.string.welcome_screen_greet_text)
            }
            enterBtn{
                isVisible()
                isDisplayed()
                hasText(R.string.google_enter_button_text)
            }
        }
    }
}