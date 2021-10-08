package com.example.htmlcompose.html.handler

import com.example.htmlcompose.html.HtmlHeader
import com.example.htmlcompose.html.handler.base.HtmlTagHandler
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node

class HtmlHeadersHandler : HtmlTagHandler {
    private var text = ""
    private var header = ""

    override fun onTagOpening(node: Node) {
        text = (node as Element).text()
        header = node.nodeName()
    }

    override fun onIntermediateTagOpening(node: Node) {}
    override fun onIntermediateTagClosing(node: Node) {}

    override fun build() = HtmlHeader(
        text = text,
        headerSize = HtmlHeader.HeaderSize.values()
            .first { it.value == header }
    )

    override fun tags() = listOf("h1", "h2", "h3", "h4", "h5", "h6")
}