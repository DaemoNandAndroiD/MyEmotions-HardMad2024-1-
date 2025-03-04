package com.example.hardmad2024_1.presentation.util.classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeekStatisticsData(
    val emotionsTexts:List<String> = listOf(),
    val icons:List<Int> = listOf(),
    val date:String = "17 фев",
    val dayOfWeek:String = "Понедельник"
) : Parcelable