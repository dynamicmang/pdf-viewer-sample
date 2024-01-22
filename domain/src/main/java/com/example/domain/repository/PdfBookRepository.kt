package com.example.domain.repository

import com.example.domain.model.PdfBook
import kotlinx.coroutines.flow.Flow

interface PdfBookRepository {

    suspend fun getPdfBook(id: Long): Flow<PdfBook>

    suspend fun getPdfBookList(): Flow<List<PdfBook>>

    suspend fun insertPdfBook(entity: PdfBook): Long

    suspend fun updatePdfBookLearningDate(id: Long, date: Long)

    suspend fun updatePdfBookLearningTime(id: Long, learningTime: Long)

    suspend fun updatePdfBookmarkCount(id: Long, bookmarkCount: Int)

    suspend fun deletePdfBook(id: Long): Int

    suspend fun deletePdfBookList()

}