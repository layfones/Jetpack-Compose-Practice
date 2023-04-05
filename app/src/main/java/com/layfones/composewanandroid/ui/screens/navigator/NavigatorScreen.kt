package com.layfones.composewanandroid.ui.screens.navigator

import WanTab
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.layfones.composewanandroid.ui.screens.navigator.navigation.NavigationScreen
import com.layfones.composewanandroid.ui.screens.navigator.system.SystemScreen
import com.layfones.composewanandroid.ui.screens.navigator.tutorial.TutorialScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigatorScreen() {
    Column(Modifier.fillMaxSize()) {

        val titles = listOf("导航", "体系", "教程")
        val pagerState = rememberPagerState()

        WanTab(
            pagerState = pagerState,
            titles = titles,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(201.dp)
                .height(48.dp)
        )
        HorizontalPager(count = titles.size, state = pagerState) { page ->
            when (page) {
                0 -> NavigationScreen()
                1 -> SystemScreen()
                else -> TutorialScreen()
            }
        }
    }
}