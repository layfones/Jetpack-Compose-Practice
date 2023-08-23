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

import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupScreen(groupViewModel: GroupViewModel = hiltViewModel()) {
    val viewState = groupViewModel.viewState
    val titleData = viewState.titleData
    if (titleData.isNotEmpty()) {
        Column {
            val pagerState = rememberPagerState(pageCount = {titleData.size})
            val scope = rememberCoroutineScope()
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                edgePadding = 2.dp,
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .height(3.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                            .background(MaterialTheme.colorScheme.primary)
                    )
                },
                containerColor = MaterialTheme.colorScheme.background
            ) {
                titleData.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title.name, style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.primary)) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
            ) { page ->
                val projectListViewModel: GroupListViewModel = createGroupListViewModel(titleData[page].id)
                GroupList(projectListViewModel)
            }
        }
    }

}

