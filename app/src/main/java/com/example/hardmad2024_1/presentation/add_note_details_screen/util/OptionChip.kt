package com.example.hardmad2024_1.presentation.add_note_details_screen.util

import android.content.Context
import com.example.hardmad2024_1.R

data class OptionChip(
    val name: String,
    var isChecked: Boolean = false,
    val chipType: ChipType
)

enum class ChipType {
    ACTIVITY, HUMAN, PLACE
}

object InitialChips {
    fun getActivityChips(context: Context): List<OptionChip> {
        return context.resources.getStringArray(R.array.activity_chips)
            .map { OptionChip(name = it, chipType = ChipType.ACTIVITY) }
    }

    fun getHumanChips(context: Context): List<OptionChip> {
        return context.resources.getStringArray(R.array.human_chips)
            .map { OptionChip(name = it, chipType = ChipType.HUMAN) }
    }

    fun getPlaceChips(context: Context): List<OptionChip> {
        return context.resources.getStringArray(R.array.place_chips)
            .map { OptionChip(name = it, chipType = ChipType.PLACE) }
    }
}