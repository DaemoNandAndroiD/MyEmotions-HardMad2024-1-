package com.example.hardmad2024_1.domain.util

sealed class StateHandler<out T> {
    class Success<out T>(val data : T) : StateHandler<T>()
    data object Loading : StateHandler<Nothing>()
    data object Initial : StateHandler<Nothing>()
    class Error(val msg : String?) : StateHandler<Nothing>()
}