package com.layfones.composewanandroid.ui

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.layfones.composewanandroid.R
import com.layfones.composewanandroid.ui.screens.group.GroupScreen
import com.layfones.composewanandroid.ui.screens.home.HomeScreen
import com.layfones.composewanandroid.ui.screens.navigator.NavigatorScreen
import com.layfones.composewanandroid.ui.screens.profile.ProfileScreen
import com.layfones.composewanandroid.ui.screens.project.ProjectScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScaffold() {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var selectedScreen by remember { mutableStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedScreen = page
        }
    }
    Scaffold(
        bottomBar = {
            WanBottomBar(selectedScreen = selectedScreen,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(it)
                    }
                })
        }) {
        HorizontalPager(
            count = MainScreen.values().size,
            state = pagerState,
            userScrollEnabled = false,
            contentPadding = it
        ) { page ->
            when (MainScreen.values()[page]) {
                MainScreen.Home -> {
                    HomeScreen()
                }
                MainScreen.Project -> {
                    ProjectScreen()
                }
                MainScreen.Navigator -> {
                    NavigatorScreen()
                }
                MainScreen.Group -> {
                    GroupScreen()
                }
                else -> {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun WanBottomBar(selectedScreen: Int, onClick: (targetIndex: Int) -> Unit) {
    NavigationBar(modifier = Modifier.navigationBarsPadding()) {
        MainScreen.values().forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedScreen == index,
                onClick = { onClick(index) },
                icon = {
                    Icon(
                        painter = painterResource(if (selectedScreen == index) screen.selectedIconId else screen.unselectedIconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(stringResource(id = screen.label)) }
            )
        }
    }
}

enum class MainScreen(
    @StringRes val label: Int,
    @DrawableRes val unselectedIconId: Int,
    @DrawableRes val selectedIconId: Int
) {
    Home(R.string.home, R.drawable.ic_home, R.drawable.ic_home),
    Project(R.string.project, R.drawable.ic_project, R.drawable.ic_project),
    Navigator(R.string.navigator, R.drawable.ic_navigation, R.drawable.ic_navigation),
    Group(R.string.group, R.drawable.ic_group, R.drawable.ic_group),
    Profile(R.string.profile, R.drawable.ic_person, R.drawable.ic_person)
}