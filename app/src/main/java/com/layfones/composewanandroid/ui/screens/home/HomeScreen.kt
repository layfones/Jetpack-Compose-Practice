package com.layfones.composewanandroid.ui.screens.home

import WanTabRow
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.layfones.composewanandroid.ui.screens.home.answer.AnswerScreen
import com.layfones.composewanandroid.ui.screens.home.explore.ExploreScreen
import com.layfones.composewanandroid.ui.screens.home.square.SquareScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Column(Modifier.fillMaxSize()) {

        val titles = remember { listOf("首页", "广场", "问答") }
        val pagerState = rememberPagerState(pageCount = { titles.size })

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            WanTabRow(
                pagerState = pagerState,
                titles = titles,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(201.dp)
                    .height(48.dp)
            )
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterEnd)) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        }
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> ExploreScreen()
                1 -> SquareScreen()
                else -> AnswerScreen()
            }
        }
    }
}