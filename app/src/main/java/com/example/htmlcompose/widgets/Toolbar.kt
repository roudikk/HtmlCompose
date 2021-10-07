package com.example.htmlcompose.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.htmlcompose.theme.MyTheme
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun Toolbar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null,
    lazyListState: LazyListState? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val elevation = if (lazyListState != null && lazyListState.firstVisibleItemScrollOffset > 0) {
        4.dp
    } else {
        0.dp
    }
    Surface(
        elevation = elevation,
        color = if (elevation > 0.dp) {
            MaterialTheme.colors.surface
        } else {
            MaterialTheme.colors.background
        },
    ) {
        Column {
            Spacer(modifier = Modifier.statusBarsHeight().fillMaxWidth())
            TopAppBar(
                elevation = 0.dp,
                title = {
                    Text(
                        text = title
                    )
                },
                backgroundColor = Color.Transparent,
                navigationIcon = navigationIcon,
                actions = actions
            )
        }
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