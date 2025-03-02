package com.example.hardmad2024_1.utilities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DuringDayStatisticsData(
    val amount:Int,
    val percentages:List<Int>,
    val drawables:List<Int>
):Parcelable