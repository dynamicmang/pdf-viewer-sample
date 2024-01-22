package com.example.feature.main.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.book.navaigator.bookNavGraph
import com.example.book.navaigator.navigateBook

@Composable
fun MainNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.route
    ) {
        mainNavGraph {
            navController.navigateBook(it)
        }
        bookNavGraph {
            navController.popBackStack()
        }
    }
}