package com.example.domain.usecase

import com.example.domain.repository.RemoteBookmarkRepository
import javax.inject.Inject

class GetRemoteBookmarkUseCase @Inject constructor(
    private val repository: RemoteBookmarkRepository,
) {

    suspend operator fun invoke(pdfId: Long) = repository.getBookmark(pdfId)

}