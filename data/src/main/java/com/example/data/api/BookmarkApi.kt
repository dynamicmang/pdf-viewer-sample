package com.example.data.api

import com.example.data.model.CreateBookmark
import com.example.data.model.DeleteBookmark
import com.example.domain.model.Bookmark
import com.example.domain.model.BookmarkResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookmarkApi {

    @GET("petitPDFViewer-getBookmarks")
    suspend fun getBookmarks(@Query("userId") userId: String = "46vco4y2ah", @Query("pdfId") pdfId: String): List<Bookmark>

    @POST("petitPDFViewer-createBookmark")
    suspend fun createBookmark(@Body createBookmark: CreateBookmark): BookmarkResult

    @POST("petitPDFViewer-deleteBookmark")
    suspend fun deleteBookmark(@Body deleteBookmark: DeleteBookmark): BookmarkResult

}