package com.layfones.composewanandroid.ui.screens.home.explore

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.layfones.composewanandroid.data.services.model.Article
import com.layfones.composewanandroid.data.services.model.Banners
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.navigation.RoutePath
import com.layfones.composewanandroid.ui.components.WanBanner
import com.layfones.composewanandroid.ui.components.WanExploreItem
import com.layfones.composewanandroid.ui.components.WanStatePage
import com.layfones.composewanandroid.ui.createAppViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExploreScreen(viewModel: ExploreViewModel = hiltViewModel()) {

    val appViewModel = createAppViewModel()

    val viewState = viewModel.viewState

    val data = viewState.pagingDataFlow.collectAsLazyPagingItems()
    val navHostController = LocalNavController.current
    val refreshing = (data.loadState.refresh is LoadState.Loading && data.itemCount > 0)
    val pullRefreshState = rememberPullRefreshState(refreshing, { data.refresh() })

    LaunchedEffect(Unit) {
        appViewModel.collectFlow.collect {
            val article = data[it.index] as Article
            article.collect = it.isCollected
        }
    }


    WanStatePage(loading = data.loadState.refresh is LoadState.Loading, empty = data.itemCount == 0) {
        Box(Modifier.pullRefresh(pullRefreshState)) {
            if (data.itemCount > 0) {
                LazyColumn(Modifier.fillMaxSize(), state = viewState.listState) {
                    item {
                        Card(modifier = Modifier.padding(12.dp, 4.dp)) {
                            val banners: Banners = data[0] as Banners
                            WanBanner(banners.banners, onItemClick = { itemIndex ->
                                navHostController.navigate(RoutePath.web + "/${Uri.encode(banners.banners[itemIndex].url)}")
                            })

                        }
                    }
                    if (data.itemCount > 1) {
                        items(data.itemCount) { index ->
                            if (index >= 1) {
                                WanExploreItem(article = data[index] as Article,
                                    index = index,
                                    onItemClick = { article ->
                                        navHostController.navigate(RoutePath.web + "/${Uri.encode(article.link)}")
                                    },
                                    onAuthorNameClick = { authorId ->
                                        navHostController.navigate(RoutePath.share + "/${authorId}")
                                    },
                                    onCollectionClick = {
                                        appViewModel.articleCollectAction(it)
                                    })
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }

    }
}