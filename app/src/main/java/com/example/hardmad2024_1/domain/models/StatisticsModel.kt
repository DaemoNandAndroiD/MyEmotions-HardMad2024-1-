package com.example.hardmad2024_1.domain.models

data class StatisticsModel(
    val generalData : GeneralStatisticsModel,
    val weekData: List<WeekStatisticsModel>,
    val oftenData: List<OftenStatisticsModel>,
    val duringDayData: List<DuringDayStatisticsModel>
)