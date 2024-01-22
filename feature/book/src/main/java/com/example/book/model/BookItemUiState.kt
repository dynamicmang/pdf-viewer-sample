package com.example.book.model

data class BookItemUiState(
    val id: Long,
    val page: Int,
    val totalPage: Int,
    val isBookmarked: Boolean,
)