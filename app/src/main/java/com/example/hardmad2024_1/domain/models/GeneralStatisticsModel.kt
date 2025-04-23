package com.example.hardmad2024_1.domain.models

data class GeneralStatisticsModel (
    val recordsCount : Int,
    val circleDataList : List<CircleData>
)

data class CircleData(
    val color : EmotionColor,
    val percentage : Float
)