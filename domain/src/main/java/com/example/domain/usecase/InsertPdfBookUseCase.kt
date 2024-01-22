package com.example.domain.usecase

import com.example.domain.model.PdfBook
import com.example.domain.repository.PdfBookRepository
import javax.inject.Inject


class InsertPdfBookUseCase @Inject constructor(
    private val repository : PdfBookRepository,
) {
    suspend operator fun invoke(pdfBook: PdfBook): Long {
        return repository.insertPdfBook(pdfBook)
    }
}