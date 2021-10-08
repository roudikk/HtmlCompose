package com.example.htmlcompose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.htmlcompose.demo.DEMO_HTML
import com.example.htmlcompose.html.*
import com.example.htmlcompose.screen.composables.*
import com.example.htmlcompose.theme.MyTheme
import com.example.htmlcompose.widgets.Toolbar
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.coroutines.launch

@Composable
fun HtmlScreen(html: String) = ProvideWindowInsets {
    val viewModel = remember { HtmlViewModel(html) }
    val elements by viewModel.elements.collectAsState()

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
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text("Html Compose Demo", style = MaterialTheme.typography.h4)
            }

            item {
                Text("This html parser will provide a list of data models that can be used in any way the developer wants, in this example they are used as a list of Composable inside a LazyColumn where each html element is a lazy item.")
            }

            item {
                Text(
                    "Jsoup is being used to traverse the html, then on each element found, it would be added to one of the below data models:",
                    Modifier.padding(bottom = 16.dp)
                )
            }

            val headers = elements.filterIsInstance<HtmlHeader>()
            headers
                .forEach {
                    item {
                        LinkableItem(
                            text = it.text,
                            lazyListState = lazyListState,
                            index = elements.indexOf(it) + 3 + headers.size
                        )
                    }
                }

            val maxWidthModifier = Modifier.widthIn(max = 350.dp)
            elements.forEach { item ->
                item {
                    Column(Modifier.fillMaxWidth()) {
                        when (item) {
                            is HtmlHeader -> HtmlHeaderItem(
                                item,
                                Modifier
                                    .padding(top = 16.dp)
                            )
                            is HtmlImage -> HtmlImageItem(item, maxWidthModifier)
                            is HtmlParagraph -> HtmlParagraphItem(item)
                            is HtmlList -> if (item.ordered) {
                                HtmlOrderedListItem(item)
                            } else {
                                HtmlUnorderedListItem(item)
                            }
                            is HtmlVideo -> HtmlVideoItem(exoPlayer, item, maxWidthModifier)
                            is HtmlTable -> HtmlTableItem(
                                item,
                                maxWidthModifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LinkableItem(
    text: String,
    lazyListState: LazyListState,
    index: Int
) {
    val scope = rememberCoroutineScope()
    Row(Modifier
        .semantics(mergeDescendants = true) { }
        .clickable {
            scope.launch {
                lazyListState.scrollToItem(index)
            }
        }
    ) {
        Icon(imageVector = Icons.Default.Link, contentDescription = null)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text)
    }
}

@Preview
@Composable
private fun HtmlScreenLight() {
    MyTheme(darkTheme = false) {
        HtmlScreen(DEMO_HTML)
    }
}

@Preview
@Composable
private fun HtmlScreenDark() {
    MyTheme(darkTheme = true) {
        HtmlScreen(DEMO_HTML)
    }
}
