package com.example.htmlcompose.html

import androidx.compose.ui.geometry.Size

sealed class HtmlElement

data class HtmlParagraph(
    val text: String,
    val styles: List<HtmlStyle> = emptyList()
) : HtmlElement()

data class HtmlImage(
    val src: String,
    val size: Size?,
    val alt: String
) : HtmlElement()

data class HtmlVideo(
    val src: String,
    val size: Size?
) : HtmlElement()

data class HtmlList(
    val items: List<HtmlParagraph>,
    val ordered: Boolean
) : HtmlElement()

data class HtmlHeader(
    val text: String,
    val headerSize: HeaderSize
) : HtmlElement() {

    enum class HeaderSize(val value: String) {
        H1("h1"),
        H2("h2"),
        H3("h3"),
        H4("h4"),
        H5("h5"),
        H6("h6")
    }
}

data class HtmlTable(
    val rows: List<Row>
) : HtmlElement() {

    data class Row(
        val cells: List<Cell>
    )

    data class Cell(
        val paragraph: HtmlParagraph,
        val header: Boolean
    )
}

sealed class HtmlStyle {
    abstract val beginning: Int
    abstract val end: Int

    data class Bold(
        override val beginning: Int,
        override val end: Int
    ) : HtmlStyle()

    data class Link(
        override val beginning: Int,
        override val end: Int,
        val link: String
    ) : HtmlStyle()

    data class Italic(
        override val beginning: Int,
        override val end: Int
    ) : HtmlStyle()

    data class Underline(
        override val beginning: Int,
        override val end: Int
    ) : HtmlStyle()

    data class LineThrough(
        override val beginning: Int,
        override val end: Int
    ) : HtmlStyle()
}

internal fun String.prependBaseUrl(baseUrl: String?): String {
    return if (startsWith("http")) {
        this
    } else {
        baseUrl?.let { "$it$this" } ?: this
    }
}