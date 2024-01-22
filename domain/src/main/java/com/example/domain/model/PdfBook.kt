package com.example.domain.model

data class PdfBook(
    val id: Long = 0,
    val name: String = "",
    val thumbnail: String = "",
    val totalPage: Int = 0,
    val bookmarkCount: Int = 0,
    val learningTime: Long = 0L,
    val learningDate: Long = 0L,
)