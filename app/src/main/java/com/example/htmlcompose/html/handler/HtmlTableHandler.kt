package com.example.htmlcompose.html.handler

import com.example.htmlcompose.html.HtmlParserOptions
import com.example.htmlcompose.html.HtmlTable
import com.example.htmlcompose.html.handler.base.HtmlTagHandler
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node

class HtmlTableHandler(
    private val options: HtmlParserOptions
) : HtmlTagHandler {
    private var rows = mutableListOf<HtmlTable.Row>()
    private var cells = mutableListOf<HtmlTable.Cell>()

    override fun onTagOpening(node: Node) {
        rows = mutableListOf()
    }

    override fun onIntermediateTagOpening(node: Node) {
        when (node.nodeName()) {
            "tr" -> cells = mutableListOf()
            "td" -> cells.add(
                HtmlTable.Cell(
                    text = (node as Element).text(),
                    header = rows.size == 0 && options.firstTableRowAsHeaders
                )
            )
            "th" -> cells.add(HtmlTable.Cell((node as Element).text(), true))
        }
    }

    override fun onIntermediateTagClosing(node: Node) {
        when (node.nodeName()) {
            "tr" -> rows.add(HtmlTable.Row(cells))
        }
    }

    override fun build() = HtmlTable(rows = rows)

    override fun tags() = listOf("table")
}