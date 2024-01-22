package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteBookmark(
    @SerialName("bookmarkId") val bookmarkId: String,
)