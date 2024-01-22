package com.example.feature.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.feature.main.R
import com.example.feature.main.model.PdfBookItemUiState
import com.example.util.getLearningDate
import com.example.util.getLearningTime

@Composable
fun MainListScreen(
    pdfList: List<PdfBookItemUiState>,
    addListener: () -> Unit,
    bookListener: (Long) -> Unit,
    removeListener: (Long) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.gray_eaeaea))
    ) {
        MainToolbar(addListener)
        LazyColumn(
            contentPadding = PaddingValues(top = 10.dp)
        ) {
            items(
                items = pdfList,
                key = { it.id }
            ) {
                MainItem(
                    pdf = it,
                    bookListener = bookListener,
                    removeListener = removeListener
                )
            }
        }
    }
}

@Composable
private fun MainToolbar(addListener: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.main_title),
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = stringResource(id = R.string.main_pdf_add),
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(10.dp)
                .clickable {
                    addListener()
                }
        )
    }
}

@Composable
private fun MainItem(pdf: PdfBookItemUiState, bookListener: (Long) -> Unit, removeListener: (Long) -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
            .padding(10.dp)
            .clickable { bookListener(pdf.id) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pdf.thumbnail)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .background(color = colorResource(id = R.color.gray_eeeeee))
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .padding(end = 20.dp)
                .align(Alignment.CenterVertically)
        ) {
            Row {
                Text(
                    text = pdf.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp)
                        .clickable { removeListener(pdf.id) }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = getLearningTime(pdf.learningTime),
                fontSize = 14.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.main_pdf_count, pdf.totalPage, pdf.bookmarkCount),
                    fontSize = 16.sp,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = getLearningDate(pdf.learningDate),
                    fontSize = 16.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Preview
@Composable
fun PdfListScreenPreview() {

}