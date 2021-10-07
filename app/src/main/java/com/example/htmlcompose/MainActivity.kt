package com.example.htmlcompose

import android.graphics.Color
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.VideoFrameDecoder
import coil.fetch.VideoFrameFileFetcher
import coil.fetch.VideoFrameUriFetcher
import com.example.htmlcompose.navigation.Navigation
import com.example.htmlcompose.theme.MyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.setDecorFitsSystemWindows(window, false)

        Coil.setImageLoader(
            ImageLoader.Builder(this).componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(this@MainActivity))
                } else {
                    add(GifDecoder())
                }
                add(VideoFrameFileFetcher(this@MainActivity))
                add(VideoFrameUriFetcher(this@MainActivity))
                add(VideoFrameDecoder(this@MainActivity))
            }.build()
        )

        setContent {
            MyTheme {
                Navigation()
            }
        }
    }
}