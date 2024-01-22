package com.example.data.datasource.local

import com.example.data.model.PdfBookEntity
import kotlinx.coroutines.flow.Flow

interface PdfBookDataSource {

    suspend fun getPdfBook(id: Long): Flow<PdfBookEntity>

    suspend fun getPdfBookList(): Flow<List<PdfBookEntity>>

    suspend fun insertPdfBook(entity: PdfBookEntity): Long

    suspend fun updatePdfBookLearningDate(id: Long, date: Long)

    suspend fun updatePdfBookLearningTime(id: Long, learningTime: Long)

    suspend fun updatePdfBookmarkCount(id: Long, bookmarkCount: Int)

    suspend fun deletePdfBook(id: Long): Int

    suspend fun deletePdfBookList()

}