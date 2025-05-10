package com.example.hardmad2024_1.data.room.entities.type_converters

import androidx.room.TypeConverter
import com.example.hardmad2024_1.data.room.entities.RecordDetails
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class RecordsTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromRecordDetails(details: RecordDetails): String {
        return gson.toJson(details)
    }

    @TypeConverter
    fun toRecordDetails(data: String): RecordDetails {
        val type = object : TypeToken<RecordDetails>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(data: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}