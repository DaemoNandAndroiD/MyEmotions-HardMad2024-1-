package com.example.hardmad2024_1.utilities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeneralStatisticsData (
    val recordsCount:Int = 0,
    val percentagesList:List<Float> = listOf()
):Parcelable