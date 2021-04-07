package com.example.htmlcompose.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun MainScaffold(
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val resources = LocalContext.current.resources
    var navBarHeight = 0f
    val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if (resourceId > 0) {
        navBarHeight = resources.getDimensionPixelSize(resourceId) /
                resources.displayMetrics.density
    }
    Scaffold(
        topBar = topBar,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = navBarHeight.dp, top = 0.dp),
        content = content
    )
}
