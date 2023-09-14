package com.layfones.composewanandroid.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.layfones.composewanandroid.navigation.MainScreen
import com.layfones.composewanandroid.navigation.RoutePath
import com.layfones.composewanandroid.ui.components.WanBottomBar
import com.layfones.composewanandroid.ui.screens.coin.CoinScreen
import com.layfones.composewanandroid.ui.screens.collect.CollectScreen
import com.layfones.composewanandroid.ui.screens.group.GroupScreen
import com.layfones.composewanandroid.ui.screens.home.HomeScreen
import com.layfones.composewanandroid.ui.screens.login.LoginScreen
import com.layfones.composewanandroid.ui.screens.message.MessageScreen
import com.layfones.composewanandroid.ui.screens.navigator.NavigatorScreen
import com.layfones.composewanandroid.ui.screens.profile.ProfileScreen
import com.layfones.composewanandroid.ui.screens.project.ProjectScreen
import com.layfones.composewanandroid.ui.screens.share.ShareListScreen
import com.layfones.composewanandroid.ui.screens.web.WebScreen
import kotlinx.coroutines.launch

@Composable
fun WanApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RoutePath.main) {
        composable(route = RoutePath.main) {
            MainScaffold()
        }
        composable(
            route = RoutePath.login,
        ) {
            LoginScreen()
        }
        composable(
            route = RoutePath.coin,
        ) {
            CoinScreen()
        }
        composable(
            route = RoutePath.message,
        ) {
            MessageScreen()
        }
        composable(
            route = "${RoutePath.share}/{userId}",
        ) { backStackEntry ->
            ShareListScreen(backStackEntry.arguments?.getString("userId") ?: "")
        }
        composable(
            route = RoutePath.collect,
        ) {
            CollectScreen()
        }
        composable(
            route = "${RoutePath.web}/{url}",
        ) { backStackEntry ->
            WebScreen(originalUrl = backStackEntry.arguments?.getString("url") ?: "",
                title = backStackEntry.arguments?.getString("title") ?: "")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScaffold() {

    val pagerState = rememberPagerState(pageCount = { MainScreen.values().size })
    val scope = rememberCoroutineScope()
    var selectedScreen by remember { mutableIntStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedScreen = page
        }
    }
    Scaffold(bottomBar = {
        WanBottomBar(selectedScreen = selectedScreen, onClick = {
            scope.launch {
                pagerState.scrollToPage(it)
            }
        })
    }) {
        HorizontalPager(state = pagerState,
            userScrollEnabled = false,
            contentPadding = it) { page ->
            when (MainScreen.values()[page]) {
                MainScreen.Home -> HomeScreen()
                MainScreen.Project -> ProjectScreen()
                MainScreen.Navigator -> NavigatorScreen()
                MainScreen.Group -> GroupScreen()
                else -> ProfileScreen()
            }
        }
    }
}