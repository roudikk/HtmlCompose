package com.example.htmlcompose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.htmlcompose.theme.MyTheme
import kotlin.math.ln

@Composable
fun Toolbar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null,
    lazyListState: LazyListState? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    actions: @Composable RowScope.() -> Unit = {}
) {
    val resources = LocalContext.current.resources
    var statusBarHeight = 0f
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        statusBarHeight = resources.getDimensionPixelSize(resourceId) /
                resources.displayMetrics.density
    }
    val elevation = if (lazyListState != null && lazyListState.firstVisibleItemScrollOffset > 0) {
        4.dp
    } else {
        0.dp
    }
    Box {
        TopAppBar(
            elevation = elevation,
            title = {
                Text(
                    text = title
                )
            },
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = navigationIcon,
            actions = actions,
            modifier = Modifier.padding(top = statusBarHeight.dp)
        )
        Box(
            modifier = Modifier
                .height(statusBarHeight.dp)
                .fillMaxWidth()
                .background(
                    color = if (elevation > 0.dp && darkTheme) {
                        val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
                        MaterialTheme.colors
                            .contentColorFor(backgroundColor = MaterialTheme.colors.surface)
                            .copy(alpha = alpha)
                            .compositeOver(MaterialTheme.colors.surface)
                    } else {
                        MaterialTheme.colors.background
                    }
                )
        )
    }
}

@Preview
@Composable
private fun ToolbarPreviewDark() {
    MyTheme(darkTheme = true) {
        Toolbar(title = "Dark theme title")
    }
}

@Preview
@Composable
private fun ToolbarPreviewLight() {
    MyTheme(darkTheme = false) {
        Toolbar(title = "Light theme title")
    }
}