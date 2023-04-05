package com.layfones.composewanandroid.ui.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProjectScreen(projectViewModel: ProjectViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        projectViewModel.getProjectTitle()
    }
    val viewState = projectViewModel.viewState
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
            val projectListViewModel: ProjectListViewModel = createProjectListViewModel(titleData[page].id)
            ProjectList(projectListViewModel)
        }
    }
}

