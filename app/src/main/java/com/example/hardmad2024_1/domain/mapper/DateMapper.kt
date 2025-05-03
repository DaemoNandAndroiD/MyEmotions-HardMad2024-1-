package com.example.hardmad2024_1.domain.mapper

import android.content.Context
import android.util.Log
import com.example.hardmad2024_1.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun formatDate(date: Date): String {
        val calendar = Calendar.getInstance()
        val today = Calendar.getInstance()
        val yesterday = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -1)
        }

        calendar.time = date

        val castedDate = date.toInstant().atZone(ZoneId.systemDefault())
            .toLocalDate()

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val timeString = timeFormat.format(date)

        val isToday = calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)

        val isYesterday = calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)

        return when {
            isToday -> context.getString(R.string.today, timeString)
            isYesterday -> context.getString(R.string.yesterday, timeString)
            else -> {
                when (castedDate.dayOfWeek) {
                    DayOfWeek.MONDAY -> context.getString(R.string.monday, timeString)
                    DayOfWeek.TUESDAY -> context.getString(R.string.tuesday, timeString)
                    DayOfWeek.WEDNESDAY -> context.getString(R.string.wednesday, timeString)
                    DayOfWeek.THURSDAY -> context.getString(R.string.thursday, timeString)
                    DayOfWeek.FRIDAY -> context.getString(R.string.friday, timeString)
                    DayOfWeek.SATURDAY -> context.getString(R.string.saturday, timeString)
                    DayOfWeek.SUNDAY -> context.getString(R.string.sunday, timeString)
                }
            }
        }
    }

    fun parseDate(dateString: String): Date? {
        val parts = dateString.split(", ")
        if (parts.size != 2) return null

        val dayPart = parts[0]
        val timePart = parts[1]

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val parsedTime = try {
            timeFormat.parse(timePart)
        } catch (e: Exception) {
            return null
        }

        val calendar = Calendar.getInstance()
        calendar.time = parsedTime

        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)

        calendar.time = Date()
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)

        Log.d("dateTest", context.getString(R.string.today))

        when (dayPart) {
            context.getString(R.string.today).split(',')[0] -> Unit
            context.getString(R.string.yesterday).split(',')[0] -> {
                calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
            else -> {
                val dayOfWeek = when (dayPart) {
                    context.getString(R.string.monday).split(',')[0] -> Calendar.MONDAY
                    context.getString(R.string.tuesday).split(',')[0] -> Calendar.TUESDAY
                    context.getString(R.string.wednesday).split(',')[0] -> Calendar.WEDNESDAY
                    context.getString(R.string.thursday).split(',')[0] -> Calendar.THURSDAY
                    context.getString(R.string.friday).split(',')[0] -> Calendar.FRIDAY
                    context.getString(R.string.saturday).split(',')[0] -> Calendar.SATURDAY
                    context.getString(R.string.sunday).split(',')[0] -> Calendar.SUNDAY
                    else -> return null
                }

                val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                val daysToSubtract = (currentDayOfWeek - dayOfWeek + 7) % 7
                calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract)
            }
        }

        return calendar.time
    }
}