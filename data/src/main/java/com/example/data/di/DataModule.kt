package com.example.data.di

import com.example.data.datasource.local.DefaultPdfBookDataSource
import com.example.data.datasource.local.PdfBookDataSource
import com.example.data.datasource.remote.DefaultRemoteBookmarkDataSource
import com.example.data.datasource.remote.RemoteBookmarkDataSource
import com.example.data.repository.DefaultPdfBookRepository
import com.example.data.repository.DefaultRemoteBookmarkRepository
import com.example.domain.repository.PdfBookRepository
import com.example.domain.repository.RemoteBookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsRemoteBookmarkDataSource(
        defaultPdfBookDataSource: DefaultRemoteBookmarkDataSource,
    ): RemoteBookmarkDataSource

    @Binds
    abstract fun bindsRemoteBookmarkRepository(
        repository: DefaultRemoteBookmarkRepository,
    ): RemoteBookmarkRepository

    @Binds
    abstract fun bindsPdfBookDataSource(
        defaultPdfBookDataSource: DefaultPdfBookDataSource,
    ): PdfBookDataSource

    @Binds
    abstract fun bindsPdfBookRepository(
        repository: DefaultPdfBookRepository,
    ): PdfBookRepository

}
