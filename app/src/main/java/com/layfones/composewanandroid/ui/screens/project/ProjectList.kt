package com.layfones.composewanandroid.ui.screens.project

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.navigation.Router
import com.layfones.composewanandroid.ui.components.ProjectItem
import com.layfones.composewanandroid.ui.components.StatePage


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectList(viewModel: ProjectListViewModel) {
    val navHostController = LocalNavController.current
    val viewState = viewModel.viewState
    val data = viewState.projectList.collectAsLazyPagingItems()
    val refreshing = data.loadState.refresh is LoadState.Loading && data.itemCount > 0
    val pullRefreshState =
        rememberPullRefreshState(refreshing = refreshing, onRefresh = { data.refresh() })
    StatePage(loading = data.loadState.refresh is LoadState.Loading ,data.itemCount == 0) {
        Box(
            Modifier
                .pullRefresh(pullRefreshState)
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                itemsIndexed(data) { _, value ->
                    ProjectItem(value!!, Modifier.clickable {
                        navHostController.navigate(Router.web + "/${Uri.encode(value.link)}")
                    })
                }
            }
            PullRefreshIndicator(
                refreshing = refreshing, state = pullRefreshState, Modifier.align(
                    Alignment.TopCenter
                )
            )
        }
    }

}