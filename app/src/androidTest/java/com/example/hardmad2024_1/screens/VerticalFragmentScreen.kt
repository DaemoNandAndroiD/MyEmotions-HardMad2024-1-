package com.example.hardmad2024_1.screens

import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.presentation.statistics.vertical_content_fragment.VerticalContentFragment
import com.kaspersky.kaspresso.screens.KScreen

object VerticalFragmentScreen : KScreen<VerticalFragmentScreen>() {


    override val layoutId: Int?
        get() = R.layout.vertical_content_fragment
    override val viewClass: Class<*>?
        get() = VerticalContentFragment::class.java
}
