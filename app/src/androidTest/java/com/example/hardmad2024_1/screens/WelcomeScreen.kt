package com.example.hardmad2024_1.screens

import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.WelcomeActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object WelcomeScreen : KScreen<WelcomeScreen>() {

    private val enterBtn = KButton{
        withId(R.id.googleButton)
    }

    fun clickEnterBtn(){
        enterBtn.click()
    }

    override val layoutId: Int?
        get() = R.layout.welcome_activity
    override val viewClass: Class<*>?
        get() = WelcomeActivity::class.java
}