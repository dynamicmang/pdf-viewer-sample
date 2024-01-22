package com.example.domain.usecase

import com.example.domain.repository.PdfBookRepository
import javax.inject.Inject

class DeletePdfBookUseCase @Inject constructor(
    private val repository: PdfBookRepository,
) {

    suspend operator fun invoke(id: Long): Int {
        return repository.deletePdfBook(id)
    }

    suspend operator fun invoke() {
        return repository.deletePdfBookList()
    }

}