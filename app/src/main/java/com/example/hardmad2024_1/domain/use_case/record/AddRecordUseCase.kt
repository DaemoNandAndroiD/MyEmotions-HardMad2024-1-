package com.example.hardmad2024_1.domain.use_case.record

import com.example.hardmad2024_1.data.room.entities.RecordDetails
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

class AddRecordUseCase @Inject constructor(
    private val repository: RecordRepository
) {
    operator fun invoke(
        userId: String,
        emotionId: String,
        recordId : String,
        activityList: List<String>,
        peopleList: List<String>,
        placeList: List<String>
    ) = flow {
        emit(StateHandler.Loading)

        repository.addRecord(
            RecordEntity(
                userId,
                emotionId,
                recordId = recordId,
                RecordDetails(
                    activityList,
                    peopleList,
                    placeList
                ),
                createdAt = Calendar.getInstance().time
            )
        )

        emit(StateHandler.Success(Unit))
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}