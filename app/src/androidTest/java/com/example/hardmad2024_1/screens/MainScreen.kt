package com.example.hardmad2024_1.screens

import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.hardmad2024_1.presentation.main_screen.MainActivity
import com.example.hardmad2024_1.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.bottomnav.KBottomNavigationView

object MainScreen : KScreen<MainScreen>() {
    private val bottomNavigationView = KBottomNavigationView{
        withId(R.id.nav_bar)
    }

    fun navigateToSettings(){
        bottomNavigationView.setSelectedItem(R.id.nav_settings)
    }

    fun navigateToStatistics(){
        bottomNavigationView.setSelectedItem(R.id.nav_statistics)
    }

    fun isDisplayedScreen(){
        isDisplayed()
    }

    override val layoutId: Int?
        get() = R.layout.main_activity
    override val viewClass: Class<*>?
        get() = MainActivity::class.java
}