package com.example.htmlcompose.html.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.htmlcompose.html.HtmlOrderedList
import com.example.htmlcompose.html.HtmlParagraph
import com.example.htmlcompose.theme.MyTheme

@Composable
fun HtmlOrderedListItem(orderedList: HtmlOrderedList, modifier: Modifier = Modifier) {
    orderedList.items.forEachIndexed { index, item ->
        Row(
            modifier
                .padding(start = 16.dp)
                .semantics(mergeDescendants = true) { }
        ) {
            Text(text = "${index + 1}. ")
            HtmlParagraphItem(paragraph = item)
        }
        if (orderedList.items.lastIndex != index) {
            Spacer(modifier = Modifier.fillMaxWidth().size(4.dp))
        }
    }
}

private val previewData = HtmlOrderedList(
    items = listOf(
        HtmlParagraph(text = "First item", styles = mutableListOf()),
        HtmlParagraph(text = "Second item", styles = mutableListOf()),
        HtmlParagraph(text = "Third item", styles = mutableListOf()),
        HtmlParagraph(text = "Fourth", styles = mutableListOf())
    )
)

@Preview
@Composable
private fun PreviewLight() {
    MyTheme(darkTheme = false) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            HtmlOrderedListItem(orderedList = previewData)
        }
    }
}

@Preview
@Composable
private fun PreviewDark() {
    MyTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            HtmlOrderedListItem(orderedList = previewData)
        }
    }
}
