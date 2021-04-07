package com.example.htmlcompose.html

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.htmlcompose.html.composables.*
import com.example.htmlcompose.theme.MyTheme
import com.example.htmlcompose.widgets.MainScaffold
import com.example.htmlcompose.widgets.Toolbar

@Composable
fun HtmlScreen(html: String) {

    val elements = parseHtml(html)
    val scrollState = rememberLazyListState()

    MainScaffold(
        topBar = {
            Toolbar(
                title = "Html",
                elevation = if (scrollState.firstVisibleItemScrollOffset > 0) {
                    4.dp
                } else {
                    0.dp
                }
            )
        },

        ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState,
            content = {
                item {
                    Card(
                        modifier = Modifier
                            .widthIn(max = 500.dp)
                            .padding(16.dp),
                        elevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            content = {
                                val modifier = Modifier.widthIn(max = 350.dp)
                                elements.forEach {
                                    when (it) {
                                        is Header -> Header(it)
                                        is Image -> Image(it, modifier)
                                        is Paragraph -> Paragraph(it)
                                        is OrderedList -> OrderedList(it)
                                        is UnorderedList -> UnorderedList(it)
                                        is Video -> Video(it, modifier)
                                        is Table -> Table(it, modifier)
                                    }
                                    Spacer(modifier = Modifier.size(12.dp))
                                }
                            })
                    }
                }
            })
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
