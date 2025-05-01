package com.example.hardmad2024_1.domain.mapper

import android.content.Context
import com.example.hardmad2024_1.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
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
                val fullDateFormat = SimpleDateFormat("yyyy-MM-dd, HH:mm", Locale.getDefault())
                fullDateFormat.format(date)
            }
        }
    }
}