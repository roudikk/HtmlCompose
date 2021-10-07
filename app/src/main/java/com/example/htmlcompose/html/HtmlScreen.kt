package com.example.htmlcompose.html

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.htmlcompose.html.composables.*
import com.example.htmlcompose.theme.MyTheme
import com.example.htmlcompose.widgets.Toolbar
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.android.exoplayer2.SimpleExoPlayer

@Composable
fun HtmlScreen(html: String) = ProvideWindowInsets {
    val elements = parseHtml(html)
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val exoPlayer = remember(context) {
        SimpleExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            Toolbar(
                title = "RoudyK/HtmlCompose",
                lazyListState = lazyListState
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            state = lazyListState,
            contentPadding = PaddingValues(16.dp)
        ) {
            val maxWidthModifier = Modifier.widthIn(max = 350.dp)
            elements.forEachIndexed { index, item ->
                item {
                    when (item) {
                        is HtmlHeader -> HtmlHeaderItem(
                            item,
                            Modifier
                                .padding(top = if (index == 0) 0.dp else 16.dp)
                        )
                        is HtmlImage -> HtmlImageItem(item, maxWidthModifier)
                        is HtmlParagraph -> HtmlParagraphItem(item)
                        is HtmlOrderedList -> HtmlOrderedListItem(item)
                        is HtmlUnorderedList -> HtmlUnorderedListItem(item)
                        is HtmlVideo -> HtmlVideoItem(exoPlayer, item, maxWidthModifier)
                        is HtmlTable -> HtmlTableItem(item, maxWidthModifier)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HtmlScreenLight() {
    MyTheme(darkTheme = false) {
        HtmlScreen(TEST_HTML)
    }
}

@Preview
@Composable
private fun HtmlScreenDark() {
    MyTheme(darkTheme = true) {
        HtmlScreen(TEST_HTML)
    }
}
