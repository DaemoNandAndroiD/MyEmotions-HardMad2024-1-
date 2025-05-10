package com.example.hardmad2024_1.domain.use_case.record

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.mapper.DateMapper
import com.example.hardmad2024_1.domain.models.JournalModel
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.util.StateHandler
import com.google.android.play.integrity.internal.l
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
        var isInterrupted = false
        var streak = 0
        var lastDayOfYear = 0

        recordsRepository.getEmotions(userId, from, to).map { map ->
            map
                .toSortedMap(compareBy<RecordEntity> { it.createdAt }.reversed())
                .map { entity ->
                    val castedDate = entity.key.createdAt.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate()

                    if (lastDayOfYear == 0 && !isInterrupted) {
                        if (castedDate != LocalDate.now()) {
                            isInterrupted = true
                        } else {
                            lastDayOfYear = castedDate.dayOfYear
                            streak++
                        }
                    }

                    if (castedDate == LocalDate.now()
                    ) {
                        todayCount++
                    }

                    //между годами переход потом протестить
                    if (!isInterrupted) {
                        when (castedDate.dayOfYear) {
                            lastDayOfYear - 1 -> {
                                streak++
                                lastDayOfYear--
                            }

                            lastDayOfYear -> Unit
                            else -> isInterrupted = true
                        }
                    }

                    RecordModel(
                        emotionName = entity.value.name,
                        emotionColor = entity.value.color,
                        date = dateMapper.formatDate(entity.key.createdAt),
                        icon = entity.value.icon,
                        recordId = entity.key.recordId,
                        emotionId = entity.value.emotionId
                    )
                }
        }.collect {
            emit(
                StateHandler.Success(
                    JournalModel(
                        recordModel = it,
                        todayRecordsCount = todayCount,
                        streak = streak
                    )
                )
            )
        }
    }.catch { e ->
        emit(StateHandler.Error(e.localizedMessage ?: "Неизвестная ошибка"))
    }
}