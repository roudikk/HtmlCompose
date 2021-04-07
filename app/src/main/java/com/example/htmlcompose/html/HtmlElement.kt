package com.example.htmlcompose.html

import androidx.compose.ui.geometry.Size
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.select.NodeVisitor

sealed class HtmlElement

data class Paragraph(val text: String, val styles: List<Style>) : HtmlElement()
data class Image(val src: String, val size: Size?, val alt: String) : HtmlElement()
data class Video(val src: String, val size: Size?) : HtmlElement()
data class OrderedList(val items: List<Paragraph>) : HtmlElement()
data class UnorderedList(val items: List<Paragraph>) : HtmlElement()
data class Header(val text: String, val headerSize: HeaderSize) : HtmlElement() {
    enum class HeaderSize(val value: String) {
        H1("h1"), H2("h2"), H3("h3"), H4("h4"), H5("h5"), H6("h6")
    }
}

data class Table(val rows: List<Row>) : HtmlElement() {
    data class Row(val cells: List<String>)
}

sealed class Style {
    abstract val beginning: Int
    abstract val end: Int

    data class Bold(
        override val beginning: Int,
        override val end: Int
    ) : Style()

    data class Link(
        override val beginning: Int,
        override val end: Int,
        val link: String
    ) : Style()

    data class Italic(
        override val beginning: Int,
        override val end: Int
    ) : Style()

    data class Underline(
        override val beginning: Int,
        override val end: Int
    ) : Style()

    data class LineThrough(
        override val beginning: Int,
        override val end: Int
    ) : Style()
}

@OptIn(ExperimentalStdlibApi::class)
fun parseHtml(html: String): List<HtmlElement> {
    val document = Jsoup.parse(html)

    // Video
    var currentVideoSize: Size? = null

    // Paragraph
    var currentParagraph = StringBuilder("")
    var currentParagraphStyles = mutableListOf<Style>()
    var currentText: String
    val currentStyleStartIndex: HashMap<String, Int> = hashMapOf()
    var currentHyperlink = ""
    var buildingTable = false

    // List
    var currentListItems = mutableListOf<Paragraph>()
    var traversingList = false

    return buildList {
        val nodeVisitor = object : NodeVisitor {

            override fun head(node: Node, depth: Int) {
                when (node.nodeName()) {
                    "video" -> {
                        if (buildingTable) return
                        val width = node.attr("width")
                        val height = node.attr("height")
                        runCatching {
                            currentVideoSize = Size(width.toFloat(), height.toFloat())
                        }
                    }
                    "source" -> {
                        if (buildingTable) return
                        val source = node.attr("src")
                        if (source != null) {
                            add(Video(source, currentVideoSize))
                        }
                    }
                    "p" -> {
                        if (buildingTable) return
                        currentParagraphStyles = mutableListOf()
                        currentParagraph = StringBuilder("")
                    }
                    "b", "a", "u", "s", "i" -> {
                        if (buildingTable) return
                        currentStyleStartIndex[node.nodeName()] = currentParagraph.length
                        if (node.nodeName() == "a") {
                            currentHyperlink = node.attr("href")
                        }
                    }
                    "h1", "h2", "h3", "h4", "h5", "h6" -> {
                        if (buildingTable) return
                        add(
                            Header(
                                text = (node as Element).text(),
                                headerSize = Header.HeaderSize.values()
                                    .first { it.value == node.nodeName() }
                            )
                        )
                    }
                    "#text" -> {
                        if (buildingTable) return
                        currentText = node.attr("#text")
                        currentParagraph.append(currentText)
                    }
                    "ol", "ul" -> {
                        if (buildingTable) return
                        traversingList = true
                        currentListItems = mutableListOf()
                        node.childNodes()
                            .filter { it.nodeName().contains("li") }.forEach {
                                currentParagraphStyles = mutableListOf()
                                currentParagraph = StringBuilder()
                                it.traverse(this)
                            }
                        traversingList = false
                    }
                    "img" -> {
                        if (buildingTable) return
                        add(
                            Image(
                                src = node.attr("src"),
                                size = try {
                                    Size(
                                        width = node.attr("width").toFloat(),
                                        height = node.attr("height").toFloat()
                                    )
                                } catch (e: Throwable) {
                                    null
                                },
                                alt = node.attr("alt"),
                            )
                        )
                    }
                    "table" -> {
                        buildingTable = true
                        val nodes = if (node.outerHtml().contains("tbody")) {
                            node.childNodes().first { it.outerHtml().contains("tbody") }
                                .childNodes()
                        } else {
                            node.childNodes()
                        }

                        val rows = mutableListOf<Table.Row>()
                        nodes
                            .filterIsInstance<Element>()
                            .forEach {
                                val cells = mutableListOf<String>()
                                it.childNodes()
                                    .filterIsInstance<Element>()
                                    .forEach { childNode -> cells.add(childNode.text()) }
                                rows.add(Table.Row(cells))
                            }

                        add(Table(rows))
                    }
                }
            }

            override fun tail(node: Node, depth: Int) {
                val startIndex = currentStyleStartIndex[node.nodeName()] ?: 0
                when (node.nodeName()) {
                    "b" -> {
                        if (buildingTable) return
                        currentParagraphStyles.add(
                            Style.Bold(
                                beginning = startIndex,
                                end = currentParagraph.length
                            )
                        )
                    }
                    "a" -> {
                        if (buildingTable) return
                        currentParagraphStyles.add(
                            Style.Link(
                                beginning = startIndex,
                                end = currentParagraph.length,
                                link = currentHyperlink
                            )
                        )
                    }
                    "li" -> {
                        if (traversingList) {
                            currentListItems.add(
                                Paragraph(
                                    text = currentParagraph.toString(),
                                    styles = mutableListOf<Style>().apply {
                                        addAll(currentParagraphStyles)
                                    }.toList()
                                )
                            )
                        }
                    }
                    "ul" -> {
                        add(UnorderedList(items = currentListItems))
                    }
                    "ol" -> {
                        add(OrderedList(items = currentListItems))
                    }
                    "u" -> {
                        if (buildingTable) return
                        currentParagraphStyles.add(
                            Style.Underline(
                                beginning = startIndex,
                                end = currentParagraph.length
                            )
                        )
                    }
                    "s" -> {
                        if (buildingTable) return
                        currentParagraphStyles.add(
                            Style.LineThrough(
                                beginning = startIndex,
                                end = currentParagraph.length
                            )
                        )
                    }
                    "i" -> {
                        if (buildingTable) return
                        currentParagraphStyles.add(
                            Style.Italic(
                                beginning = startIndex,
                                end = currentParagraph.length
                            )
                        )
                    }
                    "p" -> {
                        if (buildingTable) return
                        add(
                            Paragraph(
                                text = currentParagraph.toString(),
                                styles = mutableListOf<Style>().apply {
                                    addAll(currentParagraphStyles)
                                }.toList()
                            )
                        )
                    }
                    "table" -> {
                        buildingTable = false
                    }
                }
            }
        }
        document.traverse(nodeVisitor)
    }
}
