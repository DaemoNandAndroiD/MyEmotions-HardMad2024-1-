package com.example.hardmad2024_1.domain.use_case.record

import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.mapper.DateMapper
import com.example.hardmad2024_1.domain.models.RecordExtModel
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Thread.State
import javax.inject.Inject

class GetRecordUseCase @Inject constructor(
    private val repository: RecordRepository,
    private val dateMapper: DateMapper
) {
    operator fun invoke(id: String) = flow {
        emit(StateHandler.Loading)

        val map = repository.getRecord(id)
        val entity = map.entries.first()

        emit(
            StateHandler.Success(
                RecordExtModel(
                    recordModel = RecordModel(
                        emotionName = entity.value.name,
                        emotionColor = entity.value.color,
                        date = dateMapper.formatDate(entity.key.createdAt),
                        icon = entity.value.icon,
                        recordId = entity.key.recordId,
                        emotionId = entity.value.emotionId
                    ),
                    activities = entity.key.recordDetails.activities,
                    peoples = entity.key.recordDetails.peoples,
                    places = entity.key.recordDetails.places,
                )

            )
        )
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}