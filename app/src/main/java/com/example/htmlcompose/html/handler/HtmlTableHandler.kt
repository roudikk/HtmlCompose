package com.example.htmlcompose.html.handler

import com.example.htmlcompose.html.HtmlParagraph
import com.example.htmlcompose.html.HtmlParserOptions
import com.example.htmlcompose.html.HtmlTable
import com.example.htmlcompose.html.handler.base.HtmlTagHandler
import org.jsoup.nodes.Node

class HtmlTableHandler(
    private val options: HtmlParserOptions
) : HtmlTagHandler {
    private var rows = mutableListOf<HtmlTable.Row>()
    private var cells = mutableListOf<HtmlTable.Cell>()
    private var paragraphHandler = HtmlParagraphHandler()

    override fun onTagOpening(node: Node) {
        rows = mutableListOf()
        cells = mutableListOf()
        paragraphHandler = HtmlParagraphHandler()
    }

    override fun onIntermediateTagOpening(node: Node) {
        when (node.nodeName()) {
            "tr" -> cells = mutableListOf()
            "td", "th" -> paragraphHandler = HtmlParagraphHandler()
            "#text", "b", "a", "u", "s", "i" -> paragraphHandler.onIntermediateTagOpening(node)
        }
    }

    override fun onIntermediateTagClosing(node: Node) {
        when (node.nodeName()) {
            "b", "a", "u", "s", "i" -> paragraphHandler.onIntermediateTagClosing(node)
            "td", "th" -> cells.add(
                HtmlTable.Cell(
                    paragraph = paragraphHandler.build() as HtmlParagraph,
                    header = node.nodeName() == "th" || options.firstTableRowAsHeaders && rows.isEmpty()
                )
            )
            "tr" -> rows.add(HtmlTable.Row(cells))
        }
    }

    override fun build() = HtmlTable(rows = rows)

    override fun tags() = listOf("table")
}