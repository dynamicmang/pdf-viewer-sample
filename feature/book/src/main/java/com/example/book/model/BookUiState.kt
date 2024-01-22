package com.example.book.model

sealed interface BookUiState {
    object None : BookUiState
    object Loading : BookUiState
    data class Book(
        val bookItemList: List<BookItemUiState>,
    ) : BookUiState
}