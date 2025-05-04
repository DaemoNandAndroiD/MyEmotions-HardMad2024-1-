package com.example.hardmad2024_1.domain.models

data class OftenStatisticsModel(
    val emotionText : Int,
    val percent : Float,
    val amount : Int,
    val icon : Int,
    val color: EmotionColor
)
