package com.example.htmlcompose.html.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.htmlcompose.html.Image
import com.example.htmlcompose.widgets.RemoteImage

@Composable
fun Image(image: Image, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        RemoteImage(
            imageUrl = image.src,
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
    Image(image = Image(src = "src", Size(9f, 16f), alt = "alt"))
}

@Preview
@Composable
private fun Preview1x1() {
    Image(image = Image(src = "src", Size(1f, 1f), alt = "alt"))
}
