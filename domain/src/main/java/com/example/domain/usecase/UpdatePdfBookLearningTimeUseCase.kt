package com.example.domain.usecase

import com.example.domain.repository.PdfBookRepository
import javax.inject.Inject

class UpdatePdfBookLearningTimeUseCase @Inject constructor(
    private val repository: PdfBookRepository,
) {

    suspend operator fun invoke(id: Long, learningTime:Long) {
        repository.updatePdfBookLearningTime(id, learningTime)
    }

}