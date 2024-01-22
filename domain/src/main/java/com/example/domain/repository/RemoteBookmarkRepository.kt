package com.example.domain.repository

import com.example.domain.model.Bookmark
import com.example.domain.model.BookmarkResult
import kotlinx.coroutines.flow.Flow

interface RemoteBookmarkRepository {

    suspend fun getBookmark(pdfId: Long): Flow<List<Bookmark>>

    suspend fun deleteBookmark(bookmarkId:String) : Flow<BookmarkResult>

    suspend fun createBookmark(pdfId: Long, page: Int): Flow<BookmarkResult>

}