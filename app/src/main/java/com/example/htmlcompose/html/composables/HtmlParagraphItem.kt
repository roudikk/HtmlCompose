package com.example.htmlcompose.html.composables

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
import com.example.htmlcompose.html.HtmlParagraph
import com.example.htmlcompose.html.HtmlStyle
import com.example.htmlcompose.theme.MyTheme

@Composable
fun HtmlParagraphItem(paragraph: HtmlParagraph, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val annotatedLinkString = buildAnnotatedString {
        withStyle(ParagraphStyle()) {
            append(paragraph.text)
        }

        paragraph.styles.forEach { style ->
            addStyle(
                when (style) {
                    is HtmlStyle.Bold -> SpanStyle(fontWeight = FontWeight.Bold)
                    is HtmlStyle.Italic -> SpanStyle(fontStyle = FontStyle.Italic)
                    is HtmlStyle.Link -> SpanStyle(color = MaterialTheme.colors.primary).also {
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
                    is HtmlStyle.Underline -> SpanStyle(textDecoration = TextDecoration.Underline)
                    is HtmlStyle.LineThrough -> SpanStyle(textDecoration = TextDecoration.LineThrough)
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

private val previewData = HtmlParagraph(
    text = "This is a html paragraph with multiple styling, including italic," +
            " underline, line through, bold, hyperlink, everything",
    styles = listOf(
        HtmlStyle.Italic(
            beginning = 58,
            end = 64
        ),
        HtmlStyle.Underline(
            beginning = 66,
            end = 75
        ),
        HtmlStyle.LineThrough(
            beginning = 77,
            end = 89
        ),
        HtmlStyle.Bold(
            beginning = 91,
            end = 95
        ),
        HtmlStyle.Link(
            beginning = 97,
            end = 106,
            link = ""
        ),
        HtmlStyle.Italic(
            beginning = 108,
            end = 118
        ),
        HtmlStyle.Underline(
            beginning = 108,
            end = 118
        ),
        HtmlStyle.LineThrough(
            beginning = 108,
            end = 118
        ),
        HtmlStyle.Bold(
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
            HtmlParagraphItem(paragraph = previewData)
        }
    }
}

@Preview
@Composable
private fun PreviewDark() {
    MyTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            HtmlParagraphItem(paragraph = previewData)
        }
    }
}
