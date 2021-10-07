package com.example.htmlcompose.html.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.htmlcompose.html.HtmlImage

@Composable
fun HtmlImageItem(image: HtmlImage, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberImagePainter(
                data = image.src,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = image.alt,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(
                    image.size?.let {
                        it.width / it.height
                    } ?: 1f
                ),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
private fun Preview16x9() {
    HtmlImageItem(image = HtmlImage(src = "src", Size(9f, 16f), alt = "alt"))
}

@Preview
@Composable
private fun Preview1x1() {
    HtmlImageItem(image = HtmlImage(src = "src", Size(1f, 1f), alt = "alt"))
}
