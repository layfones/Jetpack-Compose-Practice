package com.layfones.composewanandroid.ui.screens.home.answer

// noinspection UsingMaterialAndMaterial3Libraries
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
import com.layfones.composewanandroid.ui.components.PostItem
import com.layfones.composewanandroid.ui.components.StatePage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnswerScreen(viewModel: AnswerViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState
    val data = viewState.pagingData.collectAsLazyPagingItems()
    val navHostController = LocalNavController.current
    val refreshing = (data.loadState.refresh is LoadState.Loading && data.itemCount > 0)
    val pullRefreshState = rememberPullRefreshState(refreshing, { data.refresh() })

    StatePage(loading = data.loadState.refresh is LoadState.Loading, empty = data.itemCount == 0) {
        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(Modifier.fillMaxSize(), state = viewState.listState) {
                items(data.itemCount, key = data.itemKey { it.id }) { index ->
                    val article = data[index]
                    PostItem(article = article!!, onItemClick = { item ->
                        navHostController.navigate(Router.web + "/${Uri.encode(item.link)}")
                    })
                }
            }
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }

}