package com.example.hardmad2024_1.screens

import android.view.View
import com.example.hardmad2024_1.presentation.fragments.JournalFragment
import com.example.hardmad2024_1.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.image.KImageView
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

    val addBtnText = KTextView{
        withId(R.id.add_btn_text)
    }

    val title = KTextView{
        withId(R.id.title)
    }

    val records = KTextView{
        withId(R.id.records)
    }

    val perDay = KTextView{
        withId(R.id.per_day)
    }

    val streak = KTextView{
        withId(R.id.streak)
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
    val dateText = KTextView(matcher){
        withId(R.id.date)
    }
    val emotionType = KTextView(matcher){
        withId(R.id.emotion_type)
    }
    val icon = KImageView(matcher){
        withId(R.id.icon)
    }
}