package com.example.hardmad2024_1.screens

import android.view.View
import com.example.hardmad2024_1.presentation.activities.AddNoteActivity
import com.example.hardmad2024_1.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object AddNoteScreen : KScreen<AddNoteScreen>() {
    val detailsBtn = KButton{
        withId(R.id.detailsBtn)
    }

    val backBtn = KButton{
        withId(R.id.backButton)
    }

    val defaultText = KTextView{
        withId(R.id.defaultText)
    }

    val emotionTitle = KTextView{
        withId(R.id.emotionTitle)
    }

    val emotionDescription = KTextView{
        withId(R.id.emotionDescription)
    }

    val gridRV = KRecyclerView(
        builder = { withId(R.id.recyclerView) },
        itemTypeBuilder = {
            itemType(::GridItem)
        }
    )


    override val layoutId: Int?
        get() = R.layout.add_note_activity
    override val viewClass: Class<*>?
        get() = AddNoteActivity::class.java
}

class GridItem(matcher: Matcher<View>):KRecyclerItem<GridItem>(matcher){
    val circleText = KTextView(matcher){withId(R.id.emotionText)}
}