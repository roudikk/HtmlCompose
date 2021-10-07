package com.example.htmlcompose.html.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.htmlcompose.html.HtmlTable

@Composable
fun HtmlTableItem(table: HtmlTable, modifier: Modifier = Modifier) {
    val columns = table.rows.maxOf { it.cells.size }
    Grid(
        columnCount = columns,
        list = table.rows.flatMap { it.cells },
        modifier = modifier
    ) { item ->
        Text(
            text = item.text, Modifier.weight(1f),
            style = MaterialTheme.typography.body1.copy(
                fontWeight = if (item.header) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}

@Composable
fun <T> Grid(
    columnCount: Int = 1,
    list: List<T>,
    modifier: Modifier,
    child: @Composable RowScope.(dataModel: T) -> Unit
) {
    val rows = (list.size / columnCount) + (if (list.size % columnCount > 0) 1 else 0)
    Column(modifier = modifier.fillMaxWidth()) {
        for (row in 0 until rows) {
            Row {
                for (cell in 0 until columnCount) {
                    val rowIndex = (row * columnCount) + cell
                    if (rowIndex < list.size) {
                        child(list[rowIndex])
                    } else {
                        break
                    }
                }
            }
        }
    }
}
