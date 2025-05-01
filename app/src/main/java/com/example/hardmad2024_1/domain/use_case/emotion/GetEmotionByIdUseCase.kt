package com.example.hardmad2024_1.domain.use_case.emotion

import com.example.hardmad2024_1.domain.interfaces.EmotionRepository
import com.example.hardmad2024_1.domain.mapper.DateMapper
import com.example.hardmad2024_1.domain.models.toEmotionModel
import com.example.hardmad2024_1.domain.models.toRecordModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.util.Calendar
import javax.inject.Inject

class GetEmotionByIdUseCase @Inject constructor(
    private val repository: EmotionRepository,
    private val dateMapper: DateMapper
) {
    suspend operator fun invoke(id: String) = coroutineScope {
        async {
            repository.getEmotion(id)
                .toRecordModel(date = dateMapper.formatDate(Calendar.getInstance().time))
        }
    }
}