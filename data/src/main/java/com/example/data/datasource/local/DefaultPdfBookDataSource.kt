package com.example.data.datasource.local

import com.example.data.datasource.db.PdfBookDao
import com.example.data.model.PdfBookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DefaultPdfBookDataSource @Inject constructor(
    private val pdfBookDao: PdfBookDao,
) : PdfBookDataSource {

    override suspend fun getPdfBook(id: Long): Flow<PdfBookEntity> {
        return pdfBookDao.getPdfBook(id)
    }

    override suspend fun getPdfBookList(): Flow<List<PdfBookEntity>> {
        return pdfBookDao.getPdfBookList()
    }

    override suspend fun insertPdfBook(entity: PdfBookEntity): Long {
        return pdfBookDao.insertPdfBook(entity)
    }

    override suspend fun updatePdfBookLearningDate(id: Long, date: Long) {
        pdfBookDao.updatePdfBookLearningDate(id, date)
    }

    override suspend fun updatePdfBookLearningTime(id: Long, learningTime: Long) {
        val lastLearningTime = pdfBookDao.getPdfBook(id).first().learningTime
        pdfBookDao.updatePdfBookLearningTime(id, lastLearningTime + learningTime)
    }

    override suspend fun updatePdfBookmarkCount(id: Long, bookmarkCount: Int) {
        pdfBookDao.updatePdfBookmarkCount(id, bookmarkCount)
    }

    override suspend fun deletePdfBook(id: Long): Int {
        return pdfBookDao.deletePdfBook(id)
    }

    override suspend fun deletePdfBookList() {
        return pdfBookDao.deletePdfBookList()
    }

}