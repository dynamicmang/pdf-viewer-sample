package com.example.data.datasource.remote

import com.example.data.api.BookmarkApi
import com.example.data.model.CreateBookmark
import com.example.data.model.DeleteBookmark
import com.example.domain.model.Bookmark
import com.example.domain.model.BookmarkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultRemoteBookmarkDataSource @Inject constructor(
    private val api: BookmarkApi,
) : RemoteBookmarkDataSource {

    override suspend fun getBookmark(pdfId: Long): Flow<List<Bookmark>> {
        return flow {
            emit(api.getBookmarks(pdfId = pdfId.toString()))
        }
    }

    override suspend fun deleteBookmark(bookmarkId: String): Flow<BookmarkResult> {
        return flow {
            emit(api.deleteBookmark(DeleteBookmark(bookmarkId = bookmarkId)))
        }
    }

    override suspend fun createBookmark(pdfId: Long, page: Int): Flow<BookmarkResult> {
        return flow {
            emit(api.createBookmark(CreateBookmark(userId = "46vco4y2ah", pdfId = pdfId.toString(), page = page)))
        }
    }

}