package com.example.hardmad2024_1.screens

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.hardmad2024_1.JournalFragment
import com.example.hardmad2024_1.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.progress.KProgressBar
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object JournalScreen : KScreen<JournalScreen>() {
    val addBtn = KButton{
        withId(R.id.addBtn)
    }

    val title = KTextView{
        withId(R.id.title)
    }

    val progressBarEmpty = KProgressBar{
        withId(R.id.progressBarEmpty)
    }

    val emotionsList = KRecyclerView(
        builder = {withId(R.id.emotions_list)},
        itemTypeBuilder = {
            itemType(::RecordsItem)
        }
    )

    override val layoutId: Int?
        get() = R.layout.journal_fragment
    override val viewClass: Class<*>?
        get() = JournalFragment::class.java
}

class RecordsItem(matcher: Matcher<View>) : KRecyclerItem<RecordsItem>(matcher){

}