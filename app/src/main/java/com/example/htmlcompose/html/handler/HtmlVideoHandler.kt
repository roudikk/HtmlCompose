package com.example.htmlcompose.html.handler

import androidx.compose.ui.geometry.Size
import com.example.htmlcompose.html.HtmlElement
import org.jsoup.nodes.Node

class HtmlVideoHandler : HtmlTagHandler {
    private var size: Size? = null

    override fun onTagOpening(node: Node) {
        size = runCatching {
            Size(
                width = node.attr("width").toFloat(),
                height = node.attr("height").toFloat()
            )
        }.getOrNull()
    }

    override fun onIntermediateTagOpening(node: Node) {
        when(node.nodeName()){
            "source"
        }
    }

    override fun onIntermediateTagClosing(node: Node) {

    }

    override fun build(): HtmlElement {

    }

    override fun tags(): List<String> {

    }

}