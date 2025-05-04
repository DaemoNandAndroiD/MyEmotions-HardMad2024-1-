package com.example.hardmad2024_1.domain.use_case.record

import com.example.hardmad2024_1.data.room.entities.RecordEntity
import com.example.hardmad2024_1.domain.interfaces.RecordRepository
import com.example.hardmad2024_1.domain.mapper.DateMapper
import com.example.hardmad2024_1.domain.models.CircleData
import com.example.hardmad2024_1.domain.models.DuringDayStatisticsModel
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.domain.models.GeneralStatisticsModel
import com.example.hardmad2024_1.domain.models.OftenStatisticsModel
import com.example.hardmad2024_1.domain.models.RecordModel
import com.example.hardmad2024_1.domain.models.StatisticsModel
import com.example.hardmad2024_1.domain.models.TimeInterval
import com.example.hardmad2024_1.domain.models.WeekStatisticsModel
import com.example.hardmad2024_1.domain.util.StateHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetMappedRecordsForStatisticsUseCase @Inject constructor(
    private val repository: RecordRepository,
    private val dateMapper: DateMapper
) {
    operator fun invoke(userId: String, from: Date, to: Date) = flow {
        emit(StateHandler.Loading)


        repository.getEmotions(userId, from, to).map { map ->
            map.map { entity ->
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
            emit(StateHandler.Success(mapToStatisticsModel(it, from)))
        }

    }.catch { e ->
        emit(StateHandler.Error((e.stackTrace.contentDeepToString() ?: "Неизвестная ошибка").toString()))
    }

    private fun mapToStatisticsModel(records: List<RecordModel>, from: Date) = StatisticsModel(
        generalData = castToGeneralStatisticsModel(records),
        weekData = castToWeekStatisticsModel(records, from),
        oftenData = castToOftenStatisticsModel(records),
        duringDayData = castToDuringDayStatisticsModel(records)
    )


    private fun castToGeneralStatisticsModel(records: List<RecordModel>): GeneralStatisticsModel {
        val circleDataList = mutableListOf<CircleData>()
        EmotionColor.entries.forEach { entry ->
            val count = records.filter { it.emotionColor == entry }.size
            if(count!=0){
                circleDataList.add(CircleData(entry, (count.toFloat() / records.size) * 100f))
            }
        }

        val generalStatisticsModel = GeneralStatisticsModel(records.size, circleDataList)

        return generalStatisticsModel
    }

    private fun castToWeekStatisticsModel(
        records: List<RecordModel>,
        from: Date
    ): List<WeekStatisticsModel> {
        val groupedRecords = records.groupBy { record ->
            val localDate = dateMapper.parseDate(record.date)
                ?.toInstant()
                ?.atZone(ZoneId.systemDefault())
                ?.toLocalDate()
            localDate?.dayOfWeek
        }

        val weekData = mutableListOf<WeekStatisticsModel>()
        val dateFormat = SimpleDateFormat("d MMM", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = from

        DayOfWeek.entries.forEachIndexed { index, entry ->
            val data = WeekStatisticsModel(
                emotionList = groupedRecords[entry]?.map { it.emotionName } ?: emptyList(),
                icons = groupedRecords[entry]?.map { it.icon } ?: emptyList(),
                date = dateFormat.format(
                    calendar.also {
                        it.add(Calendar.DAY_OF_WEEK, 1)
                    }.time
                ),
                dayOfWeek = entry.getDisplayName(TextStyle.FULL, Locale.getDefault())
            )

            weekData.add(data)
        }

        return weekData
    }

    private fun castToOftenStatisticsModel(records: List<RecordModel>): List<OftenStatisticsModel> {
        val map = records.groupBy {
            it.emotionName
        }

        val rawPercents : Map<Int,Float> = map.mapValues { entry ->
            if (records.isNotEmpty()) entry.value.size.toFloat() / records.size.toFloat() else 0f
        }

        val maxPercent = rawPercents.values.maxOrNull() ?: 0f

        val oftenData = mutableListOf<OftenStatisticsModel>()

        map.entries.forEach {
            val rawPercent = if (records.isNotEmpty()) it.value.size.toFloat() / records.size.toFloat() else 0f
            val normalizedPercent = if (maxPercent > 0) rawPercent / maxPercent else 0f

            oftenData.add(
                OftenStatisticsModel(
                    emotionText = it.value[0].emotionName,
                    percent = normalizedPercent,
                    amount = it.value.size,
                    icon = it.value[0].icon,
                    color = it.value[0].emotionColor
                )
            )
        }

        return oftenData.sortedByDescending { it.amount }
    }

    private fun castToDuringDayStatisticsModel(records: List<RecordModel>): List<DuringDayStatisticsModel> {
        val groupedRecords = records.groupBy { record ->
            val calendar = Calendar.getInstance().apply { time = dateMapper.parseDate(record.date) }
            val hour = calendar.get(Calendar.HOUR_OF_DAY)

            TimeInterval.entries.find { it.containsHour(hour) }
        }

        val duringDayData = mutableListOf<DuringDayStatisticsModel>()

        val circleDataList = mutableListOf<CircleData>()

        groupedRecords.entries.forEach {group->
            EmotionColor.entries.forEach { entry ->
                val count = group.value.filter { it.emotionColor == entry }.size
                if(count!=0){
                    circleDataList.add(CircleData(entry, count.toFloat() / group.value.size))
                }
            }


            duringDayData.add(
                DuringDayStatisticsModel(
                    amount = group.value.size,
                    percentage = circleDataList.map { c -> c.percentage },
                    colors = circleDataList.map { c -> c.color },
                    timeInterval = group.key?: null
                )
            )

            circleDataList.clear()
        }

        return duringDayData
    }

}