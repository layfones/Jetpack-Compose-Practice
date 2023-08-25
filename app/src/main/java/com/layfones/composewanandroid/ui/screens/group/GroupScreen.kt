package com.layfones.composewanandroid.ui.screens.group

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.layfones.composewanandroid.ui.components.WanScrollableTabRow

import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupScreen(groupViewModel: GroupViewModel = hiltViewModel()) {
    val viewState = groupViewModel.viewState
    val titleData = viewState.titleData
    if (titleData.isNotEmpty()) {
        Column {
            val pagerState = rememberPagerState(pageCount = {titleData.size})
            WanScrollableTabRow(pagerState = pagerState, titles = titleData.map { it.name })
            HorizontalPager(
                state = pagerState,
            ) { page ->
                val projectListViewModel: GroupListViewModel = createGroupListViewModel(titleData[page].id)
                GroupList(projectListViewModel)
            }
        }
    }

}

