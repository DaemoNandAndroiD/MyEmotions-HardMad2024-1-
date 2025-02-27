package com.example.hardmad2024_1.utilities

import android.widget.ImageView
import android.widget.TextView

data class DayOfWeek(
    val emotionsTextViews:List<TextView>,
    val icons:List<ImageView>,
    val date:String,
    val dayOfWeek:String
)