package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Bookmark(
    val id: String,
    val page: Int,
    val pdfId: String,
    val userId: String,
    val isVisible: Boolean,
    val createdAt: String,
)