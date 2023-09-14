package com.layfones.composewanandroid.ui.screens.home.square

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.navigation.Router
import com.layfones.composewanandroid.ui.components.WanPostItem
import com.layfones.composewanandroid.ui.components.WanStatePage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SquareScreen(viewModel: SquareViewModel = hiltViewModel()) {

    val navHostController = LocalNavController.current
    val viewState = viewModel.viewState
    val squareData = viewState.pagingData.collectAsLazyPagingItems()
    val refreshing = squareData.loadState.refresh is LoadState.Loading && squareData.itemCount > 0
    val pullRefreshState =
        rememberPullRefreshState(refreshing = refreshing, onRefresh = { squareData.refresh() })

    WanStatePage(loading = squareData.loadState.refresh is LoadState.Loading,
        squareData.itemCount == 0) {
        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(Modifier.fillMaxSize(), state = viewState.listState) {
                items(squareData.itemCount, key = squareData.itemKey { it.id }) { index ->
                    val article = squareData[index]
                    WanPostItem(article!!, onItemClick = { item ->
                        navHostController.navigate(Router.web + "/${Uri.encode(item.link)}")
                    })
                }
            }
            PullRefreshIndicator(squareData.loadState.refresh is LoadState.Loading,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter))
        }
    }
}