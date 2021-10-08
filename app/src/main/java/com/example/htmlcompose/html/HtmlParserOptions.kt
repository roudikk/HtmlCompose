package com.example.htmlcompose.html

data class HtmlParserOptions(
    // Used when images and videos have relative paths instead of absolute
    val relativePathsBaseUrl: String? = null,

    // Whether or not the first row in a <table> is always a row of headers
    val firstTableRowAsHeaders: Boolean = true
) {

    class Builder {
        var relativeSourcesBaseUrl: String? = null
        var firstTableRowAsHeaders: Boolean = true

        fun build(): HtmlParserOptions {
            return HtmlParserOptions(
                relativePathsBaseUrl = relativeSourcesBaseUrl,
                firstTableRowAsHeaders = firstTableRowAsHeaders
            )
        }
    }
}