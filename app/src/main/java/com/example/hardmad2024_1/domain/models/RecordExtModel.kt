package com.example.hardmad2024_1.domain.models

data class RecordExtModel (
    val recordModel: RecordModel,
    val activities: List<String>,
    val peoples: List<String>,
    val places: List<String>
)