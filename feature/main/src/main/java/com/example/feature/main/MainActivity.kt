package com.example.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.feature.main.navigator.MainNavHost
import com.example.feature.main.ui.theme.PdfViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PdfViewerTheme {
                MainNavHost()
            }
        }
    }
}