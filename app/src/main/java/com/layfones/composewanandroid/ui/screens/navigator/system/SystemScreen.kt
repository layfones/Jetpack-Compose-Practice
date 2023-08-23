package com.layfones.composewanandroid.ui.screens.navigator.system

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.layfones.composewanandroid.ui.components.ListItemHeader
import com.layfones.composewanandroid.ui.components.StatePage

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun SystemScreen(viewModel: SystemViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState
    StatePage(loading = viewState.list.isEmpty(), empty = viewState.list.isEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = viewState.listState) {
            viewState.list.forEachIndexed { _, value ->
                stickyHeader(key = value.name + "_sys_header") {
                    ListItemHeader(title = value.name)
                }
                item(key = value.name) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(12.dp, 10.dp),
                    ) {
                        value.children.forEachIndexed { _, classify ->
                            TextButton(
                                onClick = { /*TODO*/ }, colors = ButtonDefaults.textButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer)
                                )
                            ) {
                                Text(text = classify.name)
                            }
                        }
                    }
                }
            }
        }
    }
}