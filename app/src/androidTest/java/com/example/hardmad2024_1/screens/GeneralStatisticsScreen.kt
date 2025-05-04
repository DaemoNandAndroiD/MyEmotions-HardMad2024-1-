package com.example.hardmad2024_1.screens

import com.example.hardmad2024_1.presentation.statistics.general_statistics.GeneralStatisticsFragment
import com.example.hardmad2024_1.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object GeneralStatisticsScreen : KScreen<GeneralStatisticsScreen>() {
    val title = KTextView{
        withId(R.id.title)
    }

    val circleTextView1 = KTextView{
        withId(R.id.circle_text1)
    }

    val circleTextView2 = KTextView{
        withId(R.id.circle_text2)
    }

    val circleTextView3 = KTextView{
        withId(R.id.circle_text3)
    }

    val circleTextView4 = KTextView{
        withId(R.id.circle_text4)
    }

    val recordsCount = KTextView{
        withId(R.id.recordsCount)
    }

    override val layoutId: Int?
        get() = R.layout.statistics_general
    override val viewClass: Class<*>?
        get() = GeneralStatisticsFragment::class.java
}