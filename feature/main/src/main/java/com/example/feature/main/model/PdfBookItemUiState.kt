package com.example.feature.main.model

data class PdfBookItemUiState(
    val id: Long,
    val name: String,
    val thumbnail: String,
    val totalPage: Int,
    val bookmarkCount: Int,
    var learningTime: Long = 0L,
    var learningDate: Long = 0L,
)