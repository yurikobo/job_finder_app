package com.core.common

sealed class UiState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : UiState<T>(data = data)
    class Loading<T> : UiState<T>()
    class Error<T>(message: String) : UiState<T>(message = message)

}