package com.example.hardmad2024_1.screens

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.presentation.fragments.VerticalContentFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.pager2.KViewPager2
import io.github.kakaocup.kakao.pager2.KViewPagerItem
import org.hamcrest.Matcher

object VerticalFragmentScreen : KScreen<VerticalFragmentScreen>() {


    override val layoutId: Int?
        get() = R.layout.vertical_content_fragment
    override val viewClass: Class<*>?
        get() = VerticalContentFragment::class.java
}
