package com.example.htmlcompose.html.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.htmlcompose.html.OrderedList
import com.example.htmlcompose.html.Paragraph
import com.example.htmlcompose.theme.MyTheme

@Composable
fun OrderedList(orderedList: OrderedList, modifier: Modifier = Modifier) {
    orderedList.items.forEachIndexed { index, item ->
        Row(modifier) {
            Text(
                text = "${index + 1}. ",
                modifier = Modifier.width(24.dp),
                textAlign = TextAlign.End
            )
            Paragraph(paragraph = item)
        }
        Spacer(modifier = Modifier.size(4.dp))
    }
}

private val previewData = OrderedList(
    items = listOf(
        Paragraph(text = "First item", styles = mutableListOf()),
        Paragraph(text = "Second item", styles = mutableListOf()),
        Paragraph(text = "Third item", styles = mutableListOf()),
        Paragraph(text = "Fourth", styles = mutableListOf())
    )
)

@Preview
@Composable
private fun PreviewLight() {
    MyTheme(darkTheme = false) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            OrderedList(orderedList = previewData)
        }
    }
}

@Preview
@Composable
private fun PreviewDark() {
    MyTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            OrderedList(orderedList = previewData)
        }
    }
}
