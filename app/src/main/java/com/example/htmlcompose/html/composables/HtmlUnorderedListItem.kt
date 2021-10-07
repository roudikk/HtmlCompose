package com.example.htmlcompose.html.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.htmlcompose.html.HtmlParagraph
import com.example.htmlcompose.html.HtmlUnorderedList
import com.example.htmlcompose.theme.MyTheme

@Composable
fun HtmlUnorderedListItem(orderedList: HtmlUnorderedList, modifier: Modifier = Modifier) {
    val circleTopPadding = 9.dp
    val circleSize: Dp
    with(LocalDensity.current) {
        circleSize = 6.sp.toDp()
    }

    orderedList.items.forEach {
        Row(modifier = modifier
            .semantics(mergeDescendants = true) { }
            .padding(start = 16.dp)) {
            Box(
                modifier = Modifier
                    .padding(top = circleTopPadding)
                    .size(circleSize)
                    .clip(CircleShape)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.onSurface)
                )
            }
            Spacer(modifier = Modifier.size(6.dp))
            HtmlParagraphItem(it)
        }
        Spacer(modifier = Modifier.size(4.dp))
    }
}

private val previewData = HtmlUnorderedList(
    items = listOf(
        HtmlParagraph(
            text = "First item",
            styles = mutableListOf()
        ),
        HtmlParagraph(
            text = "Second item",
            styles = mutableListOf()
        ),
        HtmlParagraph(
            text = "Third item",
            styles = mutableListOf()
        ),
        HtmlParagraph(
            text = "Fourth",
            styles = mutableListOf()
        )
    )
)

@Preview
@Composable
private fun PreviewLight() {
    MyTheme(darkTheme = false) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            HtmlUnorderedListItem(orderedList = previewData)
        }
    }
}

@Preview
@Composable
private fun PreviewDark() {
    MyTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            HtmlUnorderedListItem(orderedList = previewData)
        }
    }
}