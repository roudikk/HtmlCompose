package com.example.htmlcompose.screen.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.htmlcompose.html.HtmlParagraph
import com.example.htmlcompose.html.HtmlStyle
import com.example.htmlcompose.html.HtmlTable
import com.example.htmlcompose.theme.MyTheme

@Composable
fun HtmlTableItem(table: HtmlTable, modifier: Modifier = Modifier) {
    val columnCount = table.rows.maxOf { it.cells.size }
    val cells = table.rows.flatMap { it.cells }
    val borderColor = MaterialTheme.colors.onSurface

    Grid(
        columnCount = columnCount,
        list = cells,
        modifier = modifier
    ) { cell ->
        val index = cells.indexOf(cell)
        Box(
            Modifier
                .weight(1f)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth

                    if (cells.lastIndex - index >= columnCount) {
                        drawLine(
                            borderColor,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }

                    if ((index + 1) % columnCount != 0) {
                        drawLine(
                            borderColor,
                            Offset(size.width, 0f),
                            Offset(size.width, size.height),
                            strokeWidth
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {

            HtmlParagraphItem(
                cell.paragraph.copy(
                    styles = cell.paragraph.styles.toMutableList().apply {
                        if (cell.header) {
                            add(HtmlStyle.Bold(0, cell.paragraph.text.length))
                        }
                    }.toList()
                ),
                Modifier.padding(6.dp)
            )
        }
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colors.onSurface)
    ) {
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

private val previewData = HtmlTable(
    rows = listOf(
        HtmlTable.Row(
            cells = listOf(
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Header 1"),
                    header = true
                ),
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Header 2"),
                    header = true
                ),
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Header 3"),
                    header = true
                )
            )
        ),
        HtmlTable.Row(
            cells = listOf(
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 1,1"),
                    header = false
                ),
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 1,2"),
                    header = false
                ),
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 1,3"),
                    header = false
                )
            )
        ),
        HtmlTable.Row(
            cells = listOf(
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 2,1"),
                    header = false
                ),
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 2,2"),
                    header = false
                ),
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 2,3"),
                    header = false
                )
            )
        ),
        HtmlTable.Row(
            cells = listOf(
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 3,1"),
                    header = false
                ),
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 3,2"),
                    header = false
                ),
                HtmlTable.Cell(
                    paragraph = HtmlParagraph("Item 3,3"),
                    header = false
                )
            )
        ),
    )
)

@Preview
@Composable
private fun TableLightPreview() = MyTheme(false) {
    Surface {
        HtmlTableItem(previewData)
    }
}

@Preview
@Composable
private fun TableDarkPreview() = MyTheme(true) {
    Surface {
        HtmlTableItem(previewData)
    }
}