package com.example.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.PdfBookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PdfBookDao {
    @Query("SELECT * FROM pdf_book WHERE pdf_book.id = :id")
    fun getPdfBook(id: Long): Flow<PdfBookEntity>

    @Query("SELECT * FROM pdf_book ORDER BY learningDate desc")
    fun getPdfBookList(): Flow<List<PdfBookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPdfBook(entity: PdfBookEntity): Long

    @Query("UPDATE pdf_book SET learningDate = :date WHERE id = :id")
    suspend fun updatePdfBookLearningDate(id: Long, date: Long)

    @Query("UPDATE pdf_book SET learningTime = :learningTime WHERE id = :id")
    suspend fun updatePdfBookLearningTime(id: Long, learningTime: Long)

    @Query("UPDATE pdf_book SET bookmarkCount = :bookmarkCount WHERE id = :id")
    suspend fun updatePdfBookmarkCount(id: Long, bookmarkCount: Int)

    @Query("DELETE FROM pdf_book WHERE id = :id")
    suspend fun deletePdfBook(id: Long): Int

    @Query("DELETE FROM pdf_book")
    suspend fun deletePdfBookList()
}