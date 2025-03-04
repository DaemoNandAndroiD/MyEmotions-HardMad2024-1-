package com.example.hardmad2024_1.presentation.util.classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OftenStatisticsData(
    val emoteText:String,
    val percent:Float,
    val emotionCount:Int,
    val icon:Int,
    val drawable:Int
):Parcelable
