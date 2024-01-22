package com.example.data.repository

import com.example.data.datasource.local.PdfBookDataSource
import com.example.data.model.asPdfBook
import com.example.data.model.asPdfBooks
import com.example.data.model.toMap
import com.example.domain.model.PdfBook
import com.example.domain.repository.PdfBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultPdfBookRepository @Inject constructor(
    private val pdfBookDataSource: PdfBookDataSource,
) : PdfBookRepository {

    override suspend fun getPdfBook(id: Long): Flow<PdfBook> {
        return pdfBookDataSource.getPdfBook(id).map { it.asPdfBook() }
    }

    override suspend fun getPdfBookList(): Flow<List<PdfBook>> {
        return pdfBookDataSource.getPdfBookList().map { it.asPdfBooks() }
    }

    override suspend fun insertPdfBook(entity: PdfBook): Long {
        return pdfBookDataSource.insertPdfBook(entity.toMap())
    }

    override suspend fun updatePdfBookLearningDate(id: Long, date: Long) {
        pdfBookDataSource.updatePdfBookLearningDate(id, date)
    }

    override suspend fun updatePdfBookLearningTime(id: Long, learningTime: Long) {
        pdfBookDataSource.updatePdfBookLearningTime(id, learningTime)
    }

    override suspend fun updatePdfBookmarkCount(id: Long, bookmarkCount: Int) {
        pdfBookDataSource.updatePdfBookmarkCount(id, bookmarkCount)
    }

    override suspend fun deletePdfBook(id: Long): Int {
        return pdfBookDataSource.deletePdfBook(id)
    }

    override suspend fun deletePdfBookList() {
        return pdfBookDataSource.deletePdfBookList()
    }

}