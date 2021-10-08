/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.htmlcompose.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
fun buildTypography(darkTheme: Boolean): Typography {
    val headersColor = if (darkTheme) {
        Color.White
    } else {
        Color(0xFF292929)
    }
    val textColor = if (darkTheme) {
        Color(0xFFEEEEEE)
    } else {
        Color(0xFF353535)
    }
    return Typography(
        body1 = TextStyle(
            color = textColor,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            color = textColor,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        h1 = TextStyle(
            color = headersColor,
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp,
            letterSpacing = (-1.5).sp
        ),
        h2 = TextStyle(
            color = headersColor,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            letterSpacing = (-0.5).sp
        ),
        h3 = TextStyle(
            color = headersColor,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            letterSpacing = 0.sp
        ),
        h4 = TextStyle(
            color = headersColor,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            letterSpacing = 0.25.sp
        ),
        h5 = TextStyle(
            color = headersColor,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            letterSpacing = 0.sp
        ),
        h6 = TextStyle(
            color = headersColor,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp
        ),
    )
}
