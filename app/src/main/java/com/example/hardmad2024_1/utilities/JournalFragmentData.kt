package com.example.hardmad2024_1.utilities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JournalFragmentData(
    val date:String,
    val emoteText:String,
    val textColor:Int,
    val backgroundDrawable:Int,
    val icon:Int
) : Parcelable
