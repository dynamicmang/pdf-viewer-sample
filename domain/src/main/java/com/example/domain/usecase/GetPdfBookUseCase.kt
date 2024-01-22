package com.example.domain.usecase

import com.example.domain.model.PdfBook
import com.example.domain.repository.PdfBookRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPdfBookUseCase @Inject constructor(
    private val repository: PdfBookRepository,
) {

    suspend operator fun invoke(id: Long): Flow<PdfBook> {
        return repository.getPdfBook(id)
    }

    suspend operator fun invoke(): Flow<List<PdfBook>> {
        return repository.getPdfBookList()
    }

}