package com.example.htmlcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.htmlcompose.html.HtmlScreen
import com.example.htmlcompose.html.TEST_HTML
import com.example.htmlcompose.nested.NestedScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HtmlScreen(TEST_HTML) }
        composable("nested") { NestedScreen(navController) }
    }
}