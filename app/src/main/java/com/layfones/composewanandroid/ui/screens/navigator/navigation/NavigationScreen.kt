package com.layfones.composewanandroid.ui.screens.navigator.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.layfones.composewanandroid.ui.components.ListItemHeader
import com.layfones.composewanandroid.ui.components.StatePage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationScreen(viewModel: NavigationViewModel = hiltViewModel()) {

    val viewState = viewModel.viewState

    StatePage(loading = viewState.dataList.isEmpty(), empty = viewState.dataList.isEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            viewState.dataList.forEachIndexed { _, value ->
                stickyHeader(key = value.name + "_nav_header") { ListItemHeader(title = value.name) }
                item(key ="item_" + value.name) {
                    FlowRow(
                        mainAxisSpacing = 12.dp,
                        modifier = Modifier.padding(12.dp, 10.dp),
                        crossAxisSpacing = 1.dp
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

