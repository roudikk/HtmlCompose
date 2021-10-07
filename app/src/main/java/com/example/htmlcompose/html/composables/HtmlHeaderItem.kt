package com.example.htmlcompose.html.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.htmlcompose.html.HtmlHeader
import com.example.htmlcompose.theme.MyTheme

@Composable
fun HtmlHeaderItem(header: HtmlHeader, modifier: Modifier = Modifier) {
    Text(
        header.text,
        style = when (header.headerSize) {
            HtmlHeader.HeaderSize.H1 -> MaterialTheme.typography.h1
            HtmlHeader.HeaderSize.H2 -> MaterialTheme.typography.h2
            HtmlHeader.HeaderSize.H3 -> MaterialTheme.typography.h3
            HtmlHeader.HeaderSize.H4 -> MaterialTheme.typography.h4
            HtmlHeader.HeaderSize.H5 -> MaterialTheme.typography.h5
            HtmlHeader.HeaderSize.H6 -> MaterialTheme.typography.h6
        },
        modifier = modifier
    )
}

private val previewData = (1 until 7).map {
    HtmlHeader(text = "Header h$it", headerSize = HtmlHeader.HeaderSize.valueOf("H$it"))
}

@Preview
@Composable
private fun PreviewLight() {
    MyTheme(darkTheme = false) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            previewData.forEach {
                HtmlHeaderItem(header = it)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDark() {
    MyTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            previewData.forEach {
                HtmlHeaderItem(header = it)
            }
        }
    }
}
