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
import androidx.paging.compose.itemKey
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.navigation.Router
import com.layfones.composewanandroid.ui.components.WanBackButton
import com.layfones.composewanandroid.ui.components.CollectItem
import com.layfones.composewanandroid.ui.components.WanStatePage

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
            WanBackButton {
                navHostController.popBackStack()
            }
            Text(
                text = "收藏文章",
                Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }

        WanStatePage(
            loading = data.loadState.refresh is LoadState.Loading,
            empty = data.itemCount == 0
        ) {
            Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(data.itemCount, key = data.itemKey{ it.id }) { index ->
                        val collectBean = data[index]
                        CollectItem(collect = collectBean!!, modifier = Modifier.clickable {
                            navHostController.navigate(Router.web + "/${Uri.encode(collectBean.link)}")
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