package com.example.hardmad2024_1.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.domain.models.EmotionColor
import java.util.UUID

@Entity(
    tableName = "emotion"
)
data class EmotionEntity(
    @PrimaryKey val emotionId : String,
    val icon : Int,
    val name : Int,
    val color : EmotionColor,
    val description : Int
)

val emotionsList = listOf(
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_red_emote,
        name = R.string.rage_title,
        color = EmotionColor.RED,
        description = R.string.rage_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_red_emote,
        name = R.string.envy_title,
        color = EmotionColor.RED,
        description = R.string.envy_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_red_emote,
        name = R.string.anxiety_title,
        color = EmotionColor.RED,
        description = R.string.anxiety_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_red_emote,
        name = R.string.stress_title,
        color = EmotionColor.RED,
        description = R.string.stress_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_yellow_emote,
        name = R.string.excitement_title,
        color = EmotionColor.YELLOW,
        description = R.string.excitement_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_yellow_emote,
        name = R.string.delight_title,
        color = EmotionColor.YELLOW,
        description = R.string.delight_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_yellow_emote,
        name = R.string.confidence_title,
        color = EmotionColor.YELLOW,
        description = R.string.confidence_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_yellow_emote,
        name = R.string.happiness_title,
        color = EmotionColor.YELLOW,
        description = R.string.happiness_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_blue_emote,
        name = R.string.burnout_title,
        color = EmotionColor.BLUE,
        description = R.string.burnout_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_blue_emote,
        name = R.string.depression_title,
        color = EmotionColor.BLUE,
        description = R.string.depression_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_blue_emote,
        name = R.string.tiredness_title,
        color = EmotionColor.BLUE,
        description = R.string.tiredness_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_blue_emote,
        name = R.string.apathy_title,
        color = EmotionColor.BLUE,
        description = R.string.apathy_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_green_emote,
        name = R.string.calmness_title,
        color = EmotionColor.GREEN,
        description = R.string.calmness_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_green_emote,
        name = R.string.satisfaction_title,
        color = EmotionColor.GREEN,
        description = R.string.satisfaction_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_green_emote,
        name = R.string.gratitude_title,
        color = EmotionColor.GREEN,
        description = R.string.gratitude_description
    ),
    EmotionEntity(
        emotionId = UUID.randomUUID().toString(),
        icon = R.drawable.ic_green_emote,
        name = R.string.security_title,
        color = EmotionColor.GREEN,
        description = R.string.security_description
    )
)



