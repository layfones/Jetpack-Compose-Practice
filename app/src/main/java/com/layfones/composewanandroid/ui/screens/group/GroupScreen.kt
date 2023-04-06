package com.layfones.composewanandroid.ui.screens.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GroupScreen(groupViewModel: GroupViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        groupViewModel.getGroupTitle()
    }
    val viewState = groupViewModel.viewState
    val titleData = viewState.titleData
    Column {
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 2.dp,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .height(3.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )
            },
            backgroundColor = MaterialTheme.colorScheme.background
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
            count = titleData.size,
            state = pagerState,
        ) { page ->
            val projectListViewModel: GroupListViewModel = createGroupListViewModel(titleData[page].id)
            GroupList(projectListViewModel)
        }
    }
}

