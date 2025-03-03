package com.example.hardmad2024_1.screens

import com.example.hardmad2024_1.AddNoteDetailsActivity
import com.example.hardmad2024_1.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object AddNoteDetailsScreen:KScreen<AddNoteDetailsScreen>() {
    val saveBtn = KButton{
        withId(R.id.saveBtn)
    }

    override val layoutId: Int?
        get() = R.layout.add_note_details_activity
    override val viewClass: Class<*>?
        get() = AddNoteDetailsActivity::class.java
}