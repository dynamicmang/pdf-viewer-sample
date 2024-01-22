package com.example.book.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.book.R
import com.example.book.model.BookItemUiState
import com.example.book.model.BookUiState
import com.example.book.viewmodel.BookViewModel

@Composable
fun BookmarkSearchContent(bookUiState: BookUiState.Book, onPageClick: (Int) -> Unit, viewModel: BookViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var onlyBookmark by remember { mutableStateOf(false) }
        BookmarkSearchToolbar(
            onlyBookmark = onlyBookmark,
            onlyBookmarkListener = {
                onlyBookmark = !onlyBookmark
            }
        )
        BookSearchGridScreen(onlyBookmark, bookUiState, onPageClick, viewModel)
    }
}

@Composable
private fun BookmarkSearchToolbar(onlyBookmark: Boolean, onlyBookmarkListener: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
    ) {
        Text(
            text = stringResource(id = if (onlyBookmark) R.string.pdf_all else R.string.pdf_only_bookmark),
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { onlyBookmarkListener() }
        )
        Text(
            text = stringResource(id = R.string.search_pdf),
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp)
        )
    }
}

@Composable
private fun BookSearchGridScreen(onlyBookmark: Boolean, bookUiState: BookUiState.Book, onPageClick: (Int) -> Unit, viewModel: BookViewModel) {
    val itemList = if (onlyBookmark) {
        bookUiState.bookItemList.filter { it.isBookmarked }
    } else {
        bookUiState.bookItemList
    }
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(3),
        content = {
            items(
                count = itemList.size,
                key = { itemList[it].page }
            ) {
                BookSearchGridItem(itemList[it], onPageClick = onPageClick, viewModel = viewModel)
            }
        }
    )
}

@Composable
fun BookSearchGridItem(bookItemUiState: BookItemUiState, onPageClick: (Int) -> Unit, viewModel: BookViewModel) {
    Column(
        modifier = Modifier
            .clickable { onPageClick(bookItemUiState.page) }
    ) {
        AsyncImage(
            model = viewModel.getPageBitmap(bookItemUiState.page - 1),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .background(colorResource(id = R.color.gray_eeeeee))
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        val description = if (bookItemUiState.isBookmarked) {
            "/" + stringResource(id = R.string.pdf_bookmarked)
        } else {
            ""
        }
        Text(
            text = "${bookItemUiState.page}$description",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}