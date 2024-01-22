package com.example.domain.usecase

import com.example.domain.repository.RemoteBookmarkRepository
import javax.inject.Inject

class DeleteRemoteBookmarkUseCase @Inject constructor(
    private val repository: RemoteBookmarkRepository,
) {

    suspend operator fun invoke(bookmarkId: String) = repository.deleteBookmark(bookmarkId)

}