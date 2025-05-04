package com.example.hardmad2024_1.screens

import android.view.View
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.presentation.statistics.week_statistics.WeekStatisticsFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object PerWeekStatisticsScreen: KScreen<PerWeekStatisticsScreen>() {
    val title = KTextView{
        withId(R.id.title)
    }

    val recordsContainer = KRecyclerView(
        builder = {withId(R.id.recordsContainer)},
        itemTypeBuilder = {
            itemType(::ItemRecords)
        }
    )

    override val layoutId: Int?
        get() = R.layout.statistics_week
    override val viewClass: Class<*>?
        get() = WeekStatisticsFragment::class.java
}

class ItemRecords(matcher: Matcher<View>) : KRecyclerItem<ItemRecords>(matcher){
    val dayOfWeek = KTextView(matcher){
        withId(R.id.day_of_week)
    }

    val date = KTextView(matcher){
        withId(R.id.date)
    }
}