package com.example.hardmad2024_1.utilities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VerticalFragmentData(
    val generalData:GeneralStatisticsData = GeneralStatisticsData(),
    val weekStatisticsData: Array<WeekStatisticsData> = arrayOf(),
    val oftenStatisticsData: Array<OftenStatisticsData> = arrayOf(),
    val duringDayStatisticsData: Array<DuringDayStatisticsData> = arrayOf()
) : Parcelable