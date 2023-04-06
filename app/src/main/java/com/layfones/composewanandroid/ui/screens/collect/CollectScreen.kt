package com.layfones.composewanandroid.ui.screens.collect

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.navigation.Router
import com.layfones.composewanandroid.ui.components.BackButton
import com.layfones.composewanandroid.ui.components.CollectItem
import com.layfones.composewanandroid.ui.components.StatePage

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun CollectScreen(viewModel: CollectViewModel = hiltViewModel()) {
    val navHostController = LocalNavController.current
    val viewState = viewModel.viewState
    val data = viewState.pagingData.collectAsLazyPagingItems()
    val refreshing = (data.loadState.refresh is LoadState.Loading && data.itemCount > 0)
    val pullRefreshState = rememberPullRefreshState(refreshing, { data.refresh() })

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            BackButton {
                navHostController.popBackStack()
            }
            Text(
                text = "收藏文章",
                Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }

        StatePage(
            loading = data.loadState.refresh is LoadState.Loading,
            empty = data.itemCount == 0
        ) {
            Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
                LazyColumn(Modifier.fillMaxSize()) {
                    itemsIndexed(data) { _, value ->
                        CollectItem(collect = value!!, modifier = Modifier.clickable {
                            navHostController.navigate(Router.web + "/${Uri.encode(value.link)}")
                        })
                    }
                }
                PullRefreshIndicator(
                    refreshing,
                    pullRefreshState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}