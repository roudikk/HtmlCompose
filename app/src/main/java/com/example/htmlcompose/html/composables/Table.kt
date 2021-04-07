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
import com.example.htmlcompose.html.Table

@Composable
fun Table(table: Table, modifier: Modifier = Modifier) {
    val columns = table.rows.maxOf { it.cells.size }
    Grid(
        cols = columns,
        list = table.rows.flatMap { it.cells },
        modifier = modifier
    ) { item, isHeader ->
        Text(
            text = item, Modifier.weight(1f),
            style = MaterialTheme.typography.body1.copy(fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal)
        )
    }
}

@Composable
fun <T> Grid(
    cols: Int = 1,
    list: List<T>,
    modifier: Modifier,
    child: @Composable RowScope.(dataModel: T, Boolean) -> Unit
) {

    val rows = (list.size / cols) + (if (list.size % cols > 0) 1 else 0)

    Column(modifier = modifier.fillMaxWidth()) {

        for (r in 0 until rows) {
            Row {
                for (cell in 0 until cols) {
                    val i = (r * cols) + cell
                    if (i < list.size) {
                        child(list[i], r == 0)
                    } else {
                        break
                    }
                }
            }
        }
    }
}
