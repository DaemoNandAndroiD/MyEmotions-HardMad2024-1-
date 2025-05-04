package com.example.hardmad2024_1.domain.models

data class DuringDayStatisticsModel (
    val amount : Int,
    val percentage: List<Float>,
    val colors : List<EmotionColor>,
    val timeInterval: TimeInterval?
)

enum class TimeInterval(val startHour: Int, val endHour: Int, val label: String) {
    EARLY_MORNING(0, 5, "00:00–06:00"),
    MORNING(6, 11, "06:00–12:00"),
    AFTERNOON(12, 17, "12:00–18:00"),
    EVENING(18, 21, "18:00–22:00"),
    LATE_EVENING(22, 23, "22:00–00:00");

    fun containsHour(hour: Int): Boolean {
        return hour in startHour..endHour
    }
}