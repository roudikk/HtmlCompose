package com.example.htmlcompose.html.handler

import androidx.compose.ui.geometry.Size
import com.example.htmlcompose.html.HtmlImage
import com.example.htmlcompose.html.HtmlParserOptions
import com.example.htmlcompose.html.prependBaseUrl
import org.jsoup.nodes.Node

class HtmlImageHandler(
    private val htmlParserOptions: HtmlParserOptions
) : HtmlTagHandler {
    private var source = ""
    private var size: Size? = null
    private var contentDescription = ""

    override fun onTagOpening(node: Node) {
        source = node.attr("src").prependBaseUrl(htmlParserOptions.relativePathsBaseUrl)
        size = runCatching {
            Size(
                width = node.attr("width").toFloat(),
                height = node.attr("height").toFloat()
            )
        }.getOrNull()

        contentDescription = node.attr("alt")
    }

    override fun onIntermediateTagOpening(node: Node) {}
    override fun onIntermediateTagClosing(node: Node) {}

    override fun build() = HtmlImage(
        src = source,
        size = size,
        alt = contentDescription,
    )

    override fun tags() = listOf("img")
}