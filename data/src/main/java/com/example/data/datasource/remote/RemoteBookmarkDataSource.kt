package com.example.data.datasource.remote

import com.example.domain.model.Bookmark
import com.example.domain.model.BookmarkResult
import kotlinx.coroutines.flow.Flow

interface RemoteBookmarkDataSource {
    suspend fun getBookmark(pdfId: Long): Flow<List<Bookmark>>

    suspend fun deleteBookmark(bookmarkId: String): Flow<BookmarkResult>

    suspend fun createBookmark(pdfId: Long, page: Int): Flow<BookmarkResult>

}