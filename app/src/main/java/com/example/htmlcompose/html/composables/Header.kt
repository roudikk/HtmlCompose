package com.example.htmlcompose.html.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.htmlcompose.html.Header
import com.example.htmlcompose.theme.MyTheme

@Composable
fun Header(header: Header, modifier: Modifier = Modifier) {
    Text(
        header.text,
        style = when (header.headerSize) {
            Header.HeaderSize.H1 -> MaterialTheme.typography.h1
            Header.HeaderSize.H2 -> MaterialTheme.typography.h2
            Header.HeaderSize.H3 -> MaterialTheme.typography.h3
            Header.HeaderSize.H4 -> MaterialTheme.typography.h4
            Header.HeaderSize.H5 -> MaterialTheme.typography.h5
            Header.HeaderSize.H6 -> MaterialTheme.typography.h6
        },
        modifier = modifier
    )
}

private val previewData = (1 until 7).map {
    Header(text = "Header h$it", headerSize = Header.HeaderSize.valueOf("H$it"))
}

@Preview
@Composable
private fun PreviewLight() {
    MyTheme(darkTheme = false) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            previewData.forEach {
                Header(header = it)
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
                Header(header = it)
            }
        }
    }
}
