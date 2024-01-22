package com.example.feature.main.model

sealed interface PdfBookUiState {
    object None : PdfBookUiState
    data class PdfBooks(
        val pdfBookItemList: List<PdfBookItemUiState>,
    ) : PdfBookUiState
}