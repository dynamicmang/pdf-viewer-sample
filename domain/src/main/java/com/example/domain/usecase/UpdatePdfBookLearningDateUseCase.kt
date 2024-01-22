package com.example.domain.usecase

import com.example.domain.repository.PdfBookRepository
import javax.inject.Inject
class UpdatePdfBookLearningDateUseCase @Inject constructor(
    private val repository: PdfBookRepository,
) {

    suspend operator fun invoke(id: Long, date:Long) {
        repository.updatePdfBookLearningDate(id, date)
    }

}