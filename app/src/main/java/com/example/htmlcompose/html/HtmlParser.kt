package com.example.htmlcompose.html

import com.example.htmlcompose.html.handler.*
import com.example.htmlcompose.html.handler.base.HtmlTagHandler
import org.jsoup.Jsoup
import org.jsoup.nodes.Node
import org.jsoup.select.NodeVisitor

object HtmlParser {

    fun parseHtml(
        html: String,
        optionsBuilder: HtmlParserOptions.Builder.() -> Unit = {}
    ): List<HtmlElement> {

        val options = HtmlParserOptions.Builder()
            .apply(optionsBuilder)
            .build()

        val elements = mutableListOf<HtmlElement>()

        val document = Jsoup.parse(html)

        val htmlTagHandlers = listOf(
            HtmlParagraphHandler(),
            HtmlListHandler(),
            HtmlHeadersHandler(),
            HtmlImageHandler(options),
            HtmlVideoHandler(options),
            HtmlTableHandler(options)
        )

        var currentHtmlTagHandler: HtmlTagHandler? = null

        document.traverse(object : NodeVisitor {
            override fun head(node: Node, depth: Int) {
                if (currentHtmlTagHandler != null) {
                    currentHtmlTagHandler?.onIntermediateTagOpening(node)
                } else {
                    currentHtmlTagHandler = htmlTagHandlers.firstOrNull {
                        it.tags().contains(node.nodeName())
                    }
                    currentHtmlTagHandler?.onTagOpening(node)
                }
            }

            override fun tail(node: Node, depth: Int) {
                currentHtmlTagHandler?.let { htmlTagHandler ->
                    if (htmlTagHandler.tags().contains(node.nodeName())) {
                        elements.add(htmlTagHandler.build())
                        currentHtmlTagHandler = null
                    } else {
                        htmlTagHandler.onIntermediateTagClosing(node)
                    }
                }
            }
        })

        return elements
    }
}