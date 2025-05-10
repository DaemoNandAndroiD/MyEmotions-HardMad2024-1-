package com.example.hardmad2024_1.screens

import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.presentation.welcome_screen.WelcomeActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object WelcomeScreen : KScreen<WelcomeScreen>() {
    val greetText = KTextView{
        withId(R.id.greet_text)
    }

    val enterBtn = KButton{
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