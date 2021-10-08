package com.example.htmlcompose.html.handler

import com.example.htmlcompose.html.HtmlElement
import com.example.htmlcompose.html.HtmlParagraph
import com.example.htmlcompose.html.HtmlStyle
import com.example.htmlcompose.html.handler.base.HtmlTagHandler
import org.jsoup.nodes.Node

class HtmlParagraphHandler : HtmlTagHandler {
    private var stringBuilder = StringBuilder()
    private var styleLastStartIndex: HashMap<String, Int> = hashMapOf()
    private var paragraphStyles = mutableListOf<HtmlStyle>()
    private var hyperlink = ""

    override fun onTagOpening(node: Node) {
        stringBuilder = StringBuilder("")
        styleLastStartIndex = hashMapOf()
        paragraphStyles = mutableListOf()
        hyperlink = ""
    }

    override fun onIntermediateTagOpening(node: Node) {
        when (node.nodeName()) {
            "#text" -> {
                stringBuilder.append(node.attr("#text"))
            }
            "b", "a", "u", "s", "i" -> {
                styleLastStartIndex[node.nodeName()] = stringBuilder.length
                if (node.nodeName() == "a") {
                    hyperlink = node.attr("href")
                }
            }
        }
    }

    override fun onIntermediateTagClosing(node: Node) {
        val startIndex = styleLastStartIndex[node.nodeName()] ?: 0
        when (node.nodeName()) {
            "b" -> {
                paragraphStyles.add(
                    HtmlStyle.Bold(
                        beginning = startIndex,
                        end = stringBuilder.length
                    )
                )
            }
            "a" -> {
                paragraphStyles.add(
                    HtmlStyle.Link(
                        beginning = startIndex,
                        end = stringBuilder.length,
                        link = hyperlink
                    )
                )
            }
            "u" -> {
                paragraphStyles.add(
                    HtmlStyle.Underline(
                        beginning = startIndex,
                        end = stringBuilder.length
                    )
                )
            }
            "s" -> {
                paragraphStyles.add(
                    HtmlStyle.LineThrough(
                        beginning = startIndex,
                        end = stringBuilder.length
                    )
                )
            }
            "i" -> {
                paragraphStyles.add(
                    HtmlStyle.Italic(
                        beginning = startIndex,
                        end = stringBuilder.length
                    )
                )
            }
        }
    }

    override fun build(): HtmlElement {
        return HtmlParagraph(
            text = stringBuilder.toString(),
            styles = paragraphStyles
        )
    }

    override fun tags() = listOf("p")
}