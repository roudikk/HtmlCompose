package com.example.htmlcompose.widgets

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.core.graphics.drawable.toBitmap
import coil.imageLoader
import coil.request.ImageRequest
import com.example.htmlcompose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private data class ImageState(val painter: Painter)

@Composable
fun RemoteImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    showPlaceholder: Boolean = true,
    contentScale: ContentScale = ContentScale.Crop
) {
    val placeholderPainter =
        rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.image_placeholder))
    var state by remember { mutableStateOf(ImageState(placeholderPainter)) }
    val context = LocalContext.current

    if (state.painter == placeholderPainter) {
        LaunchedEffect(key1 = imageUrl, block = {
            CoroutineScope(Dispatchers.IO).launch {
                val drawable = context.imageLoader.execute(
                    ImageRequest.Builder(context).data(imageUrl).build()
                ).drawable
                drawable?.let {
                    withContext(Dispatchers.Main) {
                        val bitmap = drawable.toBitmap().asImageBitmap()
                        state = ImageState(BitmapPainter(bitmap))
                    }
                }
            }
        })
    }

    Crossfade(targetState = state, animationSpec = tween(200)) {
        Image(
            painter = it.painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier,
            colorFilter = if (it.painter == placeholderPainter) {
                if (showPlaceholder) {
                    null
                } else {
                    ColorFilter.tint(Color.Transparent)
                }
            } else {
                null
            }
        )
    }
}
