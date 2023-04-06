package com.layfones.composewanandroid.ui.screens.home

import WanTab
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.layfones.composewanandroid.ui.screens.home.answer.AnswerScreen
import com.layfones.composewanandroid.ui.screens.home.explore.ExploreScreen
import com.layfones.composewanandroid.ui.screens.home.square.SquareScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    Column(Modifier.fillMaxSize()) {

        val titles = remember { listOf("首页", "广场", "问答") }
        val pagerState = rememberPagerState()

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            WanTab(
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
        HorizontalPager(count = titles.size, state = pagerState) { page ->
            when (page) {
                0 -> ExploreScreen()
                1 -> SquareScreen()
                else -> AnswerScreen()
            }
        }
    }
}