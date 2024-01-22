package com.example.domain.usecase

import com.example.domain.repository.RemoteBookmarkRepository
import javax.inject.Inject

class CreateRemoteBookmarkUseCase @Inject constructor(
    private val repository: RemoteBookmarkRepository,
) {

    suspend operator fun invoke(pdfId: Long, page: Int) = repository.createBookmark(pdfId, page)

}