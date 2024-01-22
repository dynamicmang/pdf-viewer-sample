package com.example.feature.main.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feature.main.ui.MainScreen

fun NavGraphBuilder.mainNavGraph(
    onPdfClick: (Long) -> Unit,
) {
    composable(route = MainRoute.route) {
        MainScreen(onPdfClick = onPdfClick)
    }
}

object MainRoute {
    const val route = "main"
}
