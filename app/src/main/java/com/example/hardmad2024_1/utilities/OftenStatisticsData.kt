package com.example.hardmad2024_1.utilities

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
