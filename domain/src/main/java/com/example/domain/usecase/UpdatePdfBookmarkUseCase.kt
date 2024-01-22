package com.example.domain.usecase

import com.example.domain.repository.PdfBookRepository
import javax.inject.Inject

class UpdatePdfBookmarkUseCase @Inject constructor(
    private val repository: PdfBookRepository,
) {

    suspend operator fun invoke(id: Long, bookmarkCount: Int) {
        repository.updatePdfBookmarkCount(id, bookmarkCount)
    }

}