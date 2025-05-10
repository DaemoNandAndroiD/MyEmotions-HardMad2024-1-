package com.example.hardmad2024_1.domain.use_case.record

import com.example.hardmad2024_1.data.room.entities.RecordDetails
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.mapper.DateMapper
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

class EditRecordUseCase @Inject constructor(
    private val repository: RecordRepository,
    private val dateMapper: DateMapper
) {
    operator fun invoke(
        userId: String,
        recordModel: RecordModel,
        activityList: List<String>,
        peopleList: List<String>,
        placeList: List<String>,
    ) = flow {
        emit(StateHandler.Loading)

        val entity = RecordEntity(
            userId = userId,
            emotionId = recordModel.emotionId,
            recordId = recordModel.recordId,
            RecordDetails(
                activityList,
                peopleList,
                placeList
            ),
            createdAt = dateMapper.parseDate(recordModel.date) ?: Calendar.getInstance().time
        )

        repository.editEntity(entity)

        emit(StateHandler.Success(Unit))
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}