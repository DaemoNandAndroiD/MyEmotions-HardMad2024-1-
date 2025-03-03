package com.example.hardmad2024_1

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object OftenStatisticsScreen:KScreen<OftenStatisticsScreen>() {
    val title = KTextView{
        withId(R.id.title)
    }

    val rvEmotions = KRecyclerView(
        builder = {withId(R.id.emotions_container)},
        itemTypeBuilder = {
            itemType(::RVItem)
        }
    )

    override val layoutId: Int?
        get() = R.layout.statistics_often
    override val viewClass: Class<*>?
        get() = OftenStatisticsScreen::class.java
}

class RVItem(matcher: Matcher<View>) : KRecyclerItem<RVItem>(matcher){
    val emoteText = KTextView(matcher){
        withId(R.id.emoteText)
    }

    val emotionCount = KTextView(matcher){
        withId(R.id.emotion_count)
    }

    val iconView = KImageView(matcher){
        withId(R.id.icon)
    }
}