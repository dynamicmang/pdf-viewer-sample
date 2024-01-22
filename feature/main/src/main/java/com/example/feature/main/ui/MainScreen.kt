package com.example.feature.main.ui

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature.main.model.PdfBookUiState
import com.example.feature.main.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onPdfClick: (Long) -> Unit,
) {
    val pdfUiState = viewModel.pdfBookUiState.collectAsStateWithLifecycle().value
    val pdfLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data ?: return@rememberLauncherForActivityResult
            viewModel.insertPdfBook(uri)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MainContent(
            pdfBookUiState = pdfUiState,
            addListener = {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    type = "application/pdf"
                }
                pdfLauncher.launch(intent)
            },
            bookListener = {
                onPdfClick(it)
            },
            removeListener = {
                viewModel.deletePdfBook(it)
            }
        )
    }
}

@Composable
fun MainContent(
    pdfBookUiState: PdfBookUiState,
    addListener: () -> Unit,
    bookListener: (Long) -> Unit,
    removeListener: (Long) -> Unit,
) {
    when (pdfBookUiState) {
        PdfBookUiState.None -> {}
        is PdfBookUiState.PdfBooks -> {
            MainListScreen(pdfBookUiState.pdfBookItemList, addListener, bookListener, removeListener)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen {

    }
}