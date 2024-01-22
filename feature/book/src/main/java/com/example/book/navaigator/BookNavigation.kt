package com.example.book.navaigator

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.book.ui.BookScreen

fun NavController.navigateBook(id: Long) {
    navigate(BookRoute.getRoute(id))
}

fun NavGraphBuilder.bookNavGraph(
    onBackListener: () -> Unit,
) {
    composable(
        route = BookRoute.route,
        arguments = listOf(navArgument(BookRoute.id) { type = NavType.LongType })
    ) {
        BookScreen(onBackListener = onBackListener)
    }
}

object BookRoute {
    const val id = "id"
    const val route = "book/{$id}"
    fun getRoute(id: Long) = "book/$id"
}
