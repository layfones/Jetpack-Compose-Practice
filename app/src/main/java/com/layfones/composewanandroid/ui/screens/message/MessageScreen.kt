package com.layfones.composewanandroid.ui.screens.message

import WanTab
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.ui.components.BackButton
import com.layfones.composewanandroid.ui.screens.home.explore.ExploreScreen
import com.layfones.composewanandroid.ui.screens.home.square.SquareScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MessageScreen(viewModel: MessageViewModel = hiltViewModel()) {
    val navHostController = LocalNavController.current
    val titles = listOf("未读消息", "已读消息")
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
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
                WanTab(
                    pagerState = pagerState, titles = titles,
                    Modifier
                        .align(Alignment.Center)
                        .width(201.dp)
                        .height(56.dp)
                )
            }
            HorizontalPager(count = titles.size, state = pagerState) { page ->
                when (page) {
                    0 -> MessageList(0)
                    1 -> MessageList(1)
                }
            }
        }

    }
}