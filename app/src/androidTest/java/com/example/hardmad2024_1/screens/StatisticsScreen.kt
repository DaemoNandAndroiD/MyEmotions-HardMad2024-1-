package com.example.hardmad2024_1.screens

import android.view.View
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.presentation.statistics.StatisticsFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.pager2.KViewPager2
import io.github.kakaocup.kakao.pager2.KViewPagerItem
import org.hamcrest.Matcher

object StatisticsScreen : KScreen<StatisticsScreen>() {
    val viewPager = KViewPager2(
        builder = { withId(R.id.viewPagerHorizontal) },
        itemTypeBuilder = {
            itemType(::PagerItem)
        }
    )

    override val layoutId: Int?
        get() = R.layout.statistics_fragment
    override val viewClass: Class<*>?
        get() = StatisticsFragment::class.java
}

class PagerItem(matcher: Matcher<View>) : KViewPagerItem<PagerItem>(matcher){

}