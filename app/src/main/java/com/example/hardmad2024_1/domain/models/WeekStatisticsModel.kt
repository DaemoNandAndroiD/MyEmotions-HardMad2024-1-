package com.example.hardmad2024_1.domain.models

data class WeekStatisticsModel(
    val emotionList : List<Int>,
    val icons : List<Int>,
    val date : String,
    val dayOfWeek : String
)
