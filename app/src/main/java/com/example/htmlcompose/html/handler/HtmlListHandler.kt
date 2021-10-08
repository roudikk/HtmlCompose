package com.example.htmlcompose.html.handler

import com.example.htmlcompose.html.HtmlList
import com.example.htmlcompose.html.HtmlParagraph
import org.jsoup.nodes.Node

class HtmlListHandler : HtmlTagHandler {
    private var paragraphHandler = HtmlParagraphHandler()
    private var listItems = mutableListOf<HtmlParagraph>()
    private var openingTag = ""

    override fun onTagOpening(node: Node) {
        openingTag = node.nodeName()
        listItems = mutableListOf()
        paragraphHandler = HtmlParagraphHandler()
    }

    override fun onIntermediateTagOpening(node: Node) {
        when (node.nodeName()) {
            "li" -> {
                paragraphHandler = HtmlParagraphHandler()
            }
            "#text", "b", "a", "u", "s", "i" -> {
                paragraphHandler.onIntermediateTagOpening(node)
            }
        }
    }

    override fun onIntermediateTagClosing(node: Node) {
        when (node.nodeName()) {
            "li" -> {
                listItems.add(paragraphHandler.build() as HtmlParagraph)
            }
            "b", "a", "u", "s", "i" -> {
                paragraphHandler.onIntermediateTagClosing(node)
            }
        }
    }

    override fun build() = HtmlList(
        items = listItems,
        ordered = openingTag == "ol"
    )

    override fun tags() = listOf("ol", "ul")
}