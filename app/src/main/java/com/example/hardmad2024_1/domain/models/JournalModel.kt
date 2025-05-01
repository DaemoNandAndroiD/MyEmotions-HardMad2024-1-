package com.example.hardmad2024_1.domain.models

data class JournalModel(
    val recordModel: List<RecordModel>,
    val todayRecordsCount : Int,
    val streak : Int
)
