package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pdf_book")
data class PdfBookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val thumbnail: String,
    val totalPage: Int,
    val bookmarkCount: Int,
    val learningTime: Long,
    val learningDate: Long,
)