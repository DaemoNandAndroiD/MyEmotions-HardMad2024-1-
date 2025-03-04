package com.example.hardmad2024_1.screens

import com.example.hardmad2024_1.presentation.activities.AddNoteDetailsActivity
import com.example.hardmad2024_1.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object AddNoteDetailsScreen:KScreen<AddNoteDetailsScreen>() {
    val backBtn = KButton{
        withId(R.id.backButton)
    }

    val title = KTextView{
        withId(R.id.title)
    }

    val dateText = KTextView{
        withId(R.id.date)
    }
    val emotionType = KTextView{
        withId(R.id.emotion_type)
    }
    val icon = KImageView{
        withId(R.id.icon)
    }

    val noteTextActivity = KTextView{
        withId(R.id.note_activity_text)
    }

    val notePeopleActivity = KTextView{
        withId(R.id.note_human_text)
    }

    val notePlaceActivity = KTextView{
        withId(R.id.note_place_text)
    }

    val saveBtn = KButton{
        withId(R.id.saveBtn)
    }

    override val layoutId: Int?
        get() = R.layout.add_note_details_activity
    override val viewClass: Class<*>?
        get() = AddNoteDetailsActivity::class.java
}