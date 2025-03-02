package com.example.hardmad2024_1.utilities

import android.os.Parcelable
import com.example.hardmad2024_1.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeekStatisticsData(
    val emotionsTexts:List<String> = listOf(),
    val icons:List<Int> = listOf(),
    val date:String = "17 фев",
    val dayOfWeek:String = "Понедельник"
) : Parcelable