package com.example.hardmad2024_1.domain.use_case.emotion

import com.example.hardmad2024_1.domain.interfaces.EmotionRepository
import com.example.hardmad2024_1.domain.models.toEmotionModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetEmotionsUseCase @Inject constructor(
    private val repository: EmotionRepository
) {
    suspend operator fun invoke() = coroutineScope {
        async {
            repository.getAllEmotions().map {emotionEntity ->
                emotionEntity.toEmotionModel()
            }
        }
    }
}