package com.example.htmlcompose.html.composables

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import coil.request.videoFrameMillis
import com.example.htmlcompose.html.HtmlVideo
import com.example.htmlcompose.theme.MyTheme
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun HtmlVideoItem(
    exoPlayer: SimpleExoPlayer,
    video: HtmlVideo,
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        if (isPlaying) {
            AndroidView(
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                    }
                },
                modifier = modifier
                    .aspectRatio(16f / 9f)
            )
        } else {
            Box(
                modifier = modifier
                    .clickable { isPlaying = true }
                    .background(Color.Black)
                    .aspectRatio(16f / 9f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberImagePainter(video.src) {
                        videoFrameMillis(3000)
                    },
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.4f))
                        .fillMaxSize()
                )
                Icon(
                    modifier = Modifier.size(60.dp),
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Video",
                    tint = Color.White
                )
            }
        }
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(video.src)))
            exoPlayer.prepare()
        }
    }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.stop() }
    }
}
