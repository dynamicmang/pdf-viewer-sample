package com.example.book.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book.model.BookItemUiState
import com.example.book.model.BookUiState
import com.example.book.navaigator.BookRoute
import com.example.domain.model.Bookmark
import com.example.domain.usecase.CreateRemoteBookmarkUseCase
import com.example.domain.usecase.DeleteRemoteBookmarkUseCase
import com.example.domain.usecase.GetPdfBookUseCase
import com.example.domain.usecase.GetRemoteBookmarkUseCase
import com.example.domain.usecase.UpdatePdfBookLearningDateUseCase
import com.example.domain.usecase.UpdatePdfBookLearningTimeUseCase
import com.example.domain.usecase.UpdatePdfBookmarkUseCase
import com.example.util.FileManager
import com.example.util.PdfLoader
import com.example.util.PdfLoader.getBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val getPdfBookUseCase: GetPdfBookUseCase,
    private val updatePdfBookmarkUseCase: UpdatePdfBookmarkUseCase,
    private val updatePdfBookLearningDateUseCase: UpdatePdfBookLearningDateUseCase,
    private val updatePdfBookLearningTimeUseCase: UpdatePdfBookLearningTimeUseCase,
    private val getRemoteBookmarkUseCase: GetRemoteBookmarkUseCase,
    private val createRemoteBookmarkUseCase: CreateRemoteBookmarkUseCase,
    private val deleteRemoteBookmarkUseCase: DeleteRemoteBookmarkUseCase,
) : ViewModel() {

    private val id by lazy { savedStateHandle.get<Long>(BookRoute.id) }

    private val startTime = System.currentTimeMillis()

    private val _bookUiState = MutableStateFlow<BookUiState>(BookUiState.Loading)
    val bookUiState = _bookUiState.asStateFlow()

    private val bookmarkList = arrayListOf<Bookmark>()

    private lateinit var currentBook: File
    private val pdfRenderer by lazy { PdfLoader.getPdfRenderer(currentBook) }
    private val loadedPdfImageMap: HashMap<Int, Bitmap> = hashMapOf()

    init {
        getPdfBook()
        updatePdfBookLearningDate()
    }

    private fun getPdfBook() {
        viewModelScope.launch {
            if (id == null) return@launch
            _bookUiState.value = BookUiState.Loading
            getPdfBookUseCase(id!!)
                .combine(getRemoteBookmarkUseCase(id!!)) { pdfBook, bookmarks ->
                    Timber.d("update combine")
                    if (_bookUiState.value is BookUiState.Loading) {
                        bookmarkList.clear()
                        bookmarkList.addAll(bookmarks)
                    }
                    currentBook = FileManager.getPdfFile(context, pdfBook.name)
                    val bookItemUiStateList = arrayListOf<BookItemUiState>()
                    for (i in 0 until pdfRenderer.pageCount) {
                        bookItemUiStateList.add(
                            BookItemUiState(
                                id = pdfBook.id,
                                page = i + 1,
                                totalPage = pdfRenderer.pageCount,
                                isBookmarked = bookmarkList.any { it.page == i + 1 }
                            )
                        )
                    }
                    bookItemUiStateList
                }
                .catch {
                    _bookUiState.value = BookUiState.None
                    it.printStackTrace()
                }
                .collect {
                    Timber.d("update combine collect")
                    _bookUiState.value = BookUiState.Book(it)
                }
        }
    }


    fun getPageBitmap(position: Int): Bitmap {
        if (loadedPdfImageMap.contains(position).not()) {
            val bitmap = pdfRenderer.getBitmap(position)
            loadedPdfImageMap[position] = bitmap
        }
        return loadedPdfImageMap[position]!!
    }

    fun updateBookmark(page: Int) {
        viewModelScope.launch {
            if (bookmarkList.isEmpty() || bookmarkList.any { it.page == page }.not()) {
                createRemoteBookmarkUseCase(id!!, page)
                    .catch { it.printStackTrace() }
                    .collect {
                        getRemoteBookmarkUseCase(id!!)
                            .catch { it.printStackTrace() }
                            .collect {
                                bookmarkList.clear()
                                bookmarkList.addAll(it)
                                updatePdfBookmarkUseCase(id!!, bookmarkList.size)
                            }
                    }
            } else {
                val bookmark = bookmarkList.first { it.page == page }
                deleteRemoteBookmarkUseCase(bookmark.id)
                    .catch { it.printStackTrace() }
                    .collect {
                        getRemoteBookmarkUseCase(id!!)
                            .catch { it.printStackTrace() }
                            .collect {
                                bookmarkList.clear()
                                bookmarkList.addAll(it)
                                updatePdfBookmarkUseCase(id!!, bookmarkList.size)
                            }
                    }
            }
        }
    }

    private fun updatePdfBookLearningDate() {
        viewModelScope.launch {
            updatePdfBookLearningDateUseCase(id!!, System.currentTimeMillis())
        }
    }

    fun updatePdfBookLearningTime() {
        viewModelScope.launch {
            val diff = System.currentTimeMillis() - startTime
            updatePdfBookLearningTimeUseCase(id!!, diff)
        }
    }

}