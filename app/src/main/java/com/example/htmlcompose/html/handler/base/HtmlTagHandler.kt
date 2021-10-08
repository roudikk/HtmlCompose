package com.example.htmlcompose.html.handler.base

import com.example.htmlcompose.html.HtmlElement
import org.jsoup.nodes.Node

interface HtmlTagHandler {
    fun onTagOpening(node: Node)
    fun onIntermediateTagOpening(node: Node)
    fun onIntermediateTagClosing(node: Node)
    fun build(): HtmlElement
    fun tags(): List<String>
}

fun List<HtmlTagHandler>.handlerFor(tag: String) = firstOrNull {
    it.tags().contains(tag)
}