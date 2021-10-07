package com.example.htmlcompose.nested

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.htmlcompose.widgets.MainScaffold
import com.example.htmlcompose.widgets.Toolbar


@Composable
fun NestedScreen(navController: NavController) {
    val scrollState = rememberLazyListState()
    MainScaffold(
        topBar = {
            Toolbar(
                title = "Nested",
                lazyListState = scrollState,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        LazyColumn(state = scrollState) {
            repeat((1..100).count()) {
                item {
                    Text(
                        text = "Hello!",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}