package com.example.book.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.book.model.BookUiState
import com.example.book.viewmodel.BookViewModel

@Composable
fun BookScreen(
    viewModel: BookViewModel = hiltViewModel(),
    onBackListener: () -> Unit,
) {
    val bookUiState = viewModel.bookUiState.collectAsStateWithLifecycle().value
    BackHandler {
        viewModel.updatePdfBookLearningTime()
        onBackListener()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        BookContent(
            bookUiState = bookUiState,
            onBookmarkListener = { page ->
                viewModel.updateBookmark(page)
            },
            viewModel = viewModel
        )
    }
}

@Composable
fun BookContent(
    bookUiState: BookUiState,
    onBookmarkListener: (Int) -> Unit,
    viewModel: BookViewModel,
) {
    when (bookUiState) {
        BookUiState.None -> {}
        BookUiState.Loading -> {
            BookLoading()
        }
        is BookUiState.Book -> {
            BookPagerScreen(bookUiState, onBookmarkListener, viewModel)
        }
    }
}

@Composable
private fun BookLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
