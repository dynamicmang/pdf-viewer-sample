package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBookmark(
    @SerialName("userId") val userId: String,
    @SerialName("pdfId") val pdfId: String,
    @SerialName("page") val page: Int,
)