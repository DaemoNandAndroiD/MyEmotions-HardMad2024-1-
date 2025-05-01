package com.example.hardmad2024_1.domain.use_case.record

import com.example.hardmad2024_1.data.room.entities.RecordEntity
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.mapper.DateMapper
import com.example.hardmad2024_1.domain.models.JournalModel
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

class GetLastRecordsUseCase @Inject constructor(
    private val recordsRepository: RecordRepository,
    private val dateMapper: DateMapper
) {
    operator fun invoke(userId: String, from: Date, to: Date) = flow {
        emit(StateHandler.Loading)

        var todayCount = 0

        recordsRepository.getEmotions(userId, from, to).map { map ->
            map.map { entity ->
                if(entity.key.createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() == LocalDate.now()){
                    todayCount++
                }

                RecordModel(
                    emotionName = entity.value.name,
                    emotionColor = entity.value.color,
                    date = dateMapper.formatDate(entity.key.createdAt),
                    icon = entity.value.icon,
                    recordId = entity.key.recordId
                )
            }
        }.collect {
            emit(StateHandler.Success(JournalModel(
                recordModel = it,
                todayRecordsCount = todayCount,
                streak = 0
            )))
        }
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}