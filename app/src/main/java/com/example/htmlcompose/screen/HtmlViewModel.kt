package com.example.htmlcompose.screen

import androidx.lifecycle.ViewModel
import com.example.htmlcompose.html.HtmlElement
import com.example.htmlcompose.html.HtmlParser
import kotlinx.coroutines.flow.MutableStateFlow

class HtmlViewModel(html: String) : ViewModel() {

    val elements = MutableStateFlow<List<HtmlElement>>(emptyList())

    init {
        if (elements.value.isEmpty()) {
            elements.value = HtmlParser.parseHtml(html) {
                // Example builder usage
                relativeSourcesBaseUrl = null
                firstTableRowAsHeaders = false
            }
        }
    }
}