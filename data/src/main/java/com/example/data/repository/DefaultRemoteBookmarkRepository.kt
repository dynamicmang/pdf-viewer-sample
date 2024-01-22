package com.example.data.repository

import com.example.data.datasource.remote.RemoteBookmarkDataSource
import com.example.domain.model.Bookmark
import com.example.domain.model.BookmarkResult
import com.example.domain.repository.RemoteBookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRemoteBookmarkRepository @Inject constructor(
    private val remoteBookmarkDataSource: RemoteBookmarkDataSource,
) : RemoteBookmarkRepository {

    override suspend fun getBookmark(pdfId: Long): Flow<List<Bookmark>> {
        return remoteBookmarkDataSource.getBookmark(pdfId)
    }

    override suspend fun deleteBookmark(bookmarkId: String): Flow<BookmarkResult> {
        return remoteBookmarkDataSource.deleteBookmark(bookmarkId)
    }

    override suspend fun createBookmark(pdfId: Long, page: Int): Flow<BookmarkResult> {
        return remoteBookmarkDataSource.createBookmark(pdfId, page)
    }

}