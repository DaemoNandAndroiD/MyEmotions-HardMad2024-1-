package com.example.hardmad2024_1.utilities

import android.content.Context

fun Int.toPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}