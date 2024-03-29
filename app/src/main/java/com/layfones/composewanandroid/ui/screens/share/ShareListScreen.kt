package com.layfones.composewanandroid.ui.screens.share

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.layfones.composewanandroid.data.services.model.CoinInfo
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.navigation.RoutePath
import com.layfones.composewanandroid.ui.components.WanBackButton
import com.layfones.composewanandroid.ui.components.WanPostItem
import com.layfones.composewanandroid.ui.components.WanStatePage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShareListScreen(userId: String, viewModel: ShareListViewModel = hiltViewModel()) {
    val navHostController = LocalNavController.current
    val viewState = viewModel.viewState

    DisposableEffect(userId) {
        viewModel.fetch(userId)
        onDispose {  }
    }
    
    val data = viewState.pagingData.collectAsLazyPagingItems()
    val info = viewState.sharerCoinInfo.collectAsState(initial = CoinInfo())
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
            Text(text = "分享文章"+info.value.nickname, Modifier.align(Alignment.Center), style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary))
        }

        WanStatePage(loading = data.loadState.refresh is LoadState.Loading,data.itemCount == 0) {
            Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
                LazyColumn(Modifier.fillMaxSize(), state = viewState.listState) {
                    items(data.itemCount, key = data.itemKey { it.id }) { index ->
                        val article = data[index]
                        WanPostItem(article = article!!, onItemClick = { item ->
                            navHostController.navigate(RoutePath.web + "/${Uri.encode(item.link)}")
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