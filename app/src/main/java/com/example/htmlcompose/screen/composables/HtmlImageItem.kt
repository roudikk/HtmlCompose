package com.example.htmlcompose.screen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.htmlcompose.html.HtmlImage

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HtmlImageItem(image: HtmlImage, modifier: Modifier = Modifier) {
    val painter = rememberImagePainter(
        data = image.src,
        builder = {
            crossfade(true)
        }
    )

    val currentModifier = if (image.size != null) {
        modifier.aspectRatio(image.size.width / image.size.height)
    } else {
        val painterState = painter.state
        if (painterState is ImagePainter.State.Success) {
            modifier.aspectRatio(with(painterState.result.drawable) {
                intrinsicWidth.toFloat() / intrinsicHeight.toFloat()
            })
        } else {
            modifier
                .fillMaxWidth()
                .height(1.dp)
        }
    }

    Image(
        painter = painter,
        contentDescription = image.alt,
        modifier = currentModifier
    )
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
