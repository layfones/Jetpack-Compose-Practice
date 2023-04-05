package com.layfones.composewanandroid.ui.screens.home.square

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.navigation.Router
import com.layfones.composewanandroid.ui.components.PostItem
import com.layfones.composewanandroid.ui.components.StatePage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SquareScreen(viewModel: SquareViewModel = hiltViewModel()) {

    val navHostController = LocalNavController.current
    val viewState = remember { viewModel.viewState }
    val squareData = viewState.pagingData.collectAsLazyPagingItems()
    val refreshing = squareData.loadState.refresh is LoadState.Loading && squareData.itemCount > 0
    val pullRefreshState = rememberPullRefreshState(refreshing = refreshing, onRefresh = { squareData.refresh() })
    StatePage(loading = squareData.loadState.refresh is LoadState.Loading,squareData.itemCount == 0) {
        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(Modifier.fillMaxSize()) {
                itemsIndexed(squareData) { index, value ->
                    PostItem(value!!, modifier = Modifier.clickable {
                        navHostController.navigate(Router.web+"/${Uri.encode(value.link)}")
                    })
                }
            }
            PullRefreshIndicator(squareData.loadState.refresh is LoadState.Loading, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}