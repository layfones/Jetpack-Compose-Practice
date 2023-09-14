package com.layfones.composewanandroid.ui.screens.navigator.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.layfones.composewanandroid.ui.components.WanListItemHeader
import com.layfones.composewanandroid.ui.components.WanStatePage

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun NavigationScreen(viewModel: NavigationViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState
    WanStatePage(loading = viewState.dataList.isEmpty(), empty = viewState.dataList.isEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = viewState.listState) {
            viewState.dataList.forEachIndexed { _, value ->
                stickyHeader(key = value.name + "_nav_header") { WanListItemHeader(title = value.name) }
                item(key = "item_" + value.name) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(12.dp, 10.dp),
                    ) {
                        value.articles.forEachIndexed { _, article ->
                            TextButton(
                                onClick = { /*TODO*/ }, colors = ButtonDefaults.textButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer)
                                )
                            ) {
                                Text(text = article.title)
                            }
                        }
                    }
                }
            }
        }
    }


}

