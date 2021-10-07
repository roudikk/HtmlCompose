package com.example.htmlcompose

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                with(rememberSystemUiController()) {
                    setNavigationBarColor(MaterialTheme.colors.background)
                    setStatusBarColor(Color.Transparent, darkIcons = !isSystemInDarkTheme())
                }
                Navigation()
            }
        }
    }
}