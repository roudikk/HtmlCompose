package com.example.htmlcompose.html.handler

import androidx.compose.ui.geometry.Size
import com.example.htmlcompose.html.HtmlParserOptions
import com.example.htmlcompose.html.HtmlVideo
import com.example.htmlcompose.html.handler.base.HtmlTagHandler
import com.example.htmlcompose.html.prependBaseUrl
import org.jsoup.nodes.Node

class HtmlVideoHandler(
    private val options: HtmlParserOptions
) : HtmlTagHandler {
    private var size: Size? = null
    private var source = ""

    override fun onTagOpening(node: Node) {
        size = runCatching {
            Size(
                width = node.attr("width").toFloat(),
                height = node.attr("height").toFloat()
            )
        }.getOrNull()
    }

    override fun onIntermediateTagOpening(node: Node) {
        when (node.nodeName()) {
            "source" -> {
                source = node.attr("src")
                    .prependBaseUrl(options.relativePathsBaseUrl)
            }
        }
    }

    override fun onIntermediateTagClosing(node: Node) {}

    override fun build() = HtmlVideo(
        src = source,
        size = size
    )

    override fun tags() = listOf("video")
}