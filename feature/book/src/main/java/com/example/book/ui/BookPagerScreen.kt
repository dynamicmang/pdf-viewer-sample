package com.example.book.ui

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.book.R
import com.example.book.model.BookUiState
import com.example.book.viewmodel.BookViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookPagerScreen(
    bookUiState: BookUiState.Book,
    onBookmarkListener: (Int) -> Unit,
    viewModel: BookViewModel,
) {
    val pagerState = rememberPagerState { bookUiState.bookItemList.size }
    var isSearch by remember { mutableStateOf(false) }
    var targetPage by remember { mutableStateOf(-1) }
    if (isSearch) {
        BookmarkSearchContent(
            bookUiState = bookUiState,
            onPageClick = {
                isSearch = false
                targetPage = it
            },
            viewModel = viewModel
        )
    }
    BookPagerContent(
        bookUiState = bookUiState,
        pagerState = pagerState,
        targetPage = targetPage,
        onBookmarkListener = onBookmarkListener,
        onSearchListener = {
            isSearch = true
        },
        viewModel = viewModel
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BookPagerContent(
    bookUiState: BookUiState.Book,
    pagerState: PagerState,
    targetPage: Int,
    onBookmarkListener: (Int) -> Unit,
    onSearchListener: () -> Unit,
    viewModel: BookViewModel,
) {
    LaunchedEffect(
        key1 = targetPage,
        block = {
            if (targetPage == -1) return@LaunchedEffect
            pagerState.scrollToPage(targetPage - 1)
        }
    )
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        BookScreenToolbar(
            state = pagerState,
            bookUiState = bookUiState,
            onSearchClick = {
                onSearchListener()
            },
            onBookmarkListener = {
                val position = pagerState.currentPage + 1
                onBookmarkListener(position)
            }
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            BookPagerItem(viewModel.getPageBitmap(it))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BookScreenToolbar(state: PagerState, bookUiState: BookUiState.Book, onSearchClick: () -> Unit, onBookmarkListener: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.search_pdf),
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { onSearchClick() }
        )
        Text(
            text = stringResource(id = if (bookUiState.bookItemList[state.currentPage].isBookmarked) R.string.pdf_bookmarked else R.string.pdf_bookmark),
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable { onBookmarkListener() }
        )
    }
}

@Composable
private fun BookPagerItem(bookBitmap: Bitmap) {
    AsyncImage(
        model = bookBitmap,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}