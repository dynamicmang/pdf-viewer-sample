package com.example.feature.main.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PdfBook
import com.example.domain.usecase.DeletePdfBookUseCase
import com.example.domain.usecase.GetPdfBookUseCase
import com.example.domain.usecase.InsertPdfBookUseCase
import com.example.feature.main.model.PdfBookItemUiState
import com.example.feature.main.model.PdfBookUiState
import com.example.util.FileManager
import com.example.util.PdfLoader
import com.example.util.PdfLoader.getThumbnailUri
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getPdfBookUseCase: GetPdfBookUseCase,
    private val insertPdfBookUseCase: InsertPdfBookUseCase,
    private val deletePdfBookUseCase: DeletePdfBookUseCase,
) : ViewModel() {

    private val _pdfBookUiState = MutableStateFlow<PdfBookUiState>(PdfBookUiState.None)
    val pdfBookUiState = _pdfBookUiState.asStateFlow()

    init {
        getPdfBookList()
    }

    private fun getPdfBookList() {
        viewModelScope.launch {
            getPdfBookUseCase().
            collect { pdfBookList ->
                _pdfBookUiState.value = PdfBookUiState.PdfBooks(
                    pdfBookItemList = pdfBookList.map { pdfBook ->
                        PdfBookItemUiState(
                            id = pdfBook.id,
                            name = pdfBook.name,
                            thumbnail = pdfBook.thumbnail,
                            totalPage = pdfBook.totalPage,
                            bookmarkCount = pdfBook.bookmarkCount,
                            learningTime = pdfBook.learningTime,
                            learningDate = pdfBook.learningDate,
                        )
                    }
                )
            }
        }
    }

    fun insertPdfBook(uri: Uri) {
        viewModelScope.launch {
            val file = FileManager.savePdfFile(context, uri)
            val pdfRenderer = PdfLoader.getPdfRenderer(file)
            val thumbnail = pdfRenderer.getThumbnailUri(context, file.name)
            val pdfBook = PdfBook(name = file.name, thumbnail = thumbnail, totalPage = pdfRenderer.pageCount, learningDate = System.currentTimeMillis())
            insertPdfBookUseCase(pdfBook)
        }
    }

    fun deletePdfBook(id: Long) {
        viewModelScope.launch {
            deletePdfBookUseCase(id)
        }
    }


}