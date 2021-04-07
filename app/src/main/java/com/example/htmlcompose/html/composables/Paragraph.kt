package com.example.htmlcompose.html.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.htmlcompose.html.Paragraph
import com.example.htmlcompose.html.Style
import com.example.htmlcompose.theme.MyTheme

@Composable
fun Paragraph(paragraph: Paragraph, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val annotatedLinkString = buildAnnotatedString {
        withStyle(ParagraphStyle()) {
            append(paragraph.text)
        }

        paragraph.styles.forEach { style ->
            Log.d("Testing", "Styling ${paragraph.text} $style")
            addStyle(
                when (style) {
                    is Style.Bold -> SpanStyle(fontWeight = FontWeight.Bold)
                    is Style.Italic -> SpanStyle(fontStyle = FontStyle.Italic)
                    is Style.Link -> SpanStyle(color = MaterialTheme.colors.secondary).also {
                        addStyle(
                            SpanStyle(
                                textDecoration = TextDecoration.Underline
                            ), style.beginning, style.end
                        )

                        addStringAnnotation(
                            "URL",
                            style.link,
                            style.beginning,
                            style.end
                        )
                    }
                    is Style.Underline -> SpanStyle(textDecoration = TextDecoration.Underline)
                    is Style.LineThrough -> SpanStyle(textDecoration = TextDecoration.LineThrough)
                }, style.beginning, style.end
            )
        }
    }
    ClickableText(
        text = annotatedLinkString,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        },
        style = MaterialTheme.typography.body1,
        modifier = modifier
    )
}

private val previewData = Paragraph(
    text = "This is a html paragraph with multiple styling, including italic," +
            " underline, line through, bold, hyperlink, everything",
    styles = listOf(
        Style.Italic(
            beginning = 58,
            end = 64
        ),
        Style.Underline(
            beginning = 66,
            end = 75
        ),
        Style.LineThrough(
            beginning = 77,
            end = 89
        ),
        Style.Bold(
            beginning = 91,
            end = 95
        ),
        Style.Link(
            beginning = 97,
            end = 106,
            link = ""
        ),
        Style.Italic(
            beginning = 108,
            end = 118
        ),
        Style.Underline(
            beginning = 108,
            end = 118
        ),
        Style.LineThrough(
            beginning = 108,
            end = 118
        ),
        Style.Bold(
            beginning = 108,
            end = 118
        )
    )
)

@Preview
@Composable
private fun PreviewLight() {
    MyTheme(darkTheme = false) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            Paragraph(paragraph = previewData)
        }
    }
}

@Preview
@Composable
private fun PreviewDark() {
    MyTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            Paragraph(paragraph = previewData)
        }
    }
}
