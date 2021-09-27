package com.example.rickmorty.utils

sealed class UiState {
    data class Success<T>(val data: T? = null) : UiState()
    data class Failed(val message: String?) : UiState()
    object Loading : UiState()
    object Empty : UiState()
}
