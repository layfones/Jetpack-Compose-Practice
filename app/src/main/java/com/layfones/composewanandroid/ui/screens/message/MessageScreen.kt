package com.layfones.composewanandroid.ui.screens.message

import WanTabRow
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.ui.components.BackButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageScreen(viewModel: MessageViewModel = hiltViewModel()) {
    val navHostController = LocalNavController.current
    val titles = remember { listOf("未读消息", "已读消息") }
    val pagerState = rememberPagerState(pageCount = {titles.size})
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {

        Column(Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                BackButton {
                    navHostController.popBackStack()
                }
                WanTabRow(
                    pagerState = pagerState, titles = titles,
                    Modifier
                        .align(Alignment.Center)
                        .width(201.dp)
                        .height(56.dp)
                )
            }
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> MessageList(0)
                    1 -> MessageList(1)
                }
            }
        }

    }
}