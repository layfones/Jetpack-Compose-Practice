package com.layfones.composewanandroid.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.layfones.composewanandroid.data.services.model.ProjectTitle
import kotlinx.coroutines.launch

/**
 * @author Leizf
 * @date 2023/8/25
 * @desc
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WanScrollableTabRow(pagerState: PagerState, titles: List<String>, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 2.dp,
        modifier = modifier,
        indicator = { tabPositions ->
            Box(modifier = Modifier
                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                .height(3.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                .background(MaterialTheme.colorScheme.primary))
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(title,
                        style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.primary))
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}