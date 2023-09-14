package com.layfones.composewanandroid.ui.screens.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.layfones.composewanandroid.R
import com.layfones.composewanandroid.account.checkLogin
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.navigation.Router
import com.layfones.composewanandroid.ui.WanAppViewModel
import com.layfones.composewanandroid.ui.components.WanListItemHeader
import com.layfones.composewanandroid.ui.components.WanSettingsListItem
import com.layfones.composewanandroid.ui.createAppViewModel
import com.layfones.composewanandroid.ui.theme.WanandroidTheme

@Preview
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val wanAppViewModel: WanAppViewModel = createAppViewModel()
    val navHostController = LocalNavController.current
    val accountInfoState = viewModel.accountInfo.collectAsState()
    val userBaseInfo = accountInfoState.value
    LazyColumn {
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(if (viewModel.isLogin) R.mipmap.ic_launcher_round else R.drawable.ic_person),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clickable {
                            if (!viewModel.isLogin) {
                                navHostController.navigate(Router.login)
                            }
                        })
                if (viewModel.isLogin) {
                    Text(
                        text = userBaseInfo.userInfo.username,
                        style = MaterialTheme.typography.displayMedium,
                    )
                } else {
                    OutlinedButton(onClick = { navHostController.navigate(Router.login) },
                        modifier = Modifier.padding(top = 8.dp)) {
                        Text(text = "点击登录")
                    }
                }
            }
        }
        item {
            WanListItemHeader(title = "个人中心")
            WanSettingsListItem(headlineText = "我的积分",
                leadingIcon = R.drawable.ic_star,
                modifier = Modifier.clickable {
                    viewModel.accountState.value.checkLogin(navHostController) {
                        navHostController.navigate(Router.coin)
                    }
                })
            WanSettingsListItem(headlineText = "消息中心",
                leadingIcon = R.drawable.ic_message,
                modifier = Modifier.clickable {
                    viewModel.accountState.value.checkLogin(navHostController) {
                        navHostController.navigate(Router.message)
                    }
                })
            WanSettingsListItem(headlineText = "分享文章",
                leadingIcon = R.drawable.ic_share,
                modifier = Modifier.clickable {
                    viewModel.accountState.value.checkLogin(navHostController) {
                        navHostController.navigate(Router.share + "/" + userBaseInfo.userInfo.id)
                    }
                })
            WanSettingsListItem(headlineText = "收藏文章",
                leadingIcon = R.drawable.ic_collect,
                modifier = Modifier.clickable {
                    viewModel.accountState.value.checkLogin(navHostController) {
                        navHostController.navigate(Router.collect)
                    }
                })
        }
        item {
            WanListItemHeader(title = "设置")
            Box {
                var expanded by remember { mutableStateOf(false) }
                WanSettingsListItem(headlineText = "深色模式",
                    leadingIcon = R.drawable.ic_bedtime,
                    modifier = Modifier.clickable {
                        expanded = true
                    },
                    supportingText = { Text(text = wanAppViewModel.theme.named) })
                DropdownMenu(expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    offset = DpOffset(125.dp, (-70).dp),
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                    DropdownMenuItem(text = { Text(text = WanandroidTheme.SYSTEM.named) },
                        onClick = {
                            wanAppViewModel.theme = WanandroidTheme.SYSTEM
                            expanded = false
                        },
                        modifier = Modifier.background(getBackgroundColor(wanAppViewModel = wanAppViewModel,
                            theme = WanandroidTheme.SYSTEM)))
                    DropdownMenuItem(text = { Text(text = WanandroidTheme.DARK.named) },
                        onClick = {
                            wanAppViewModel.theme = WanandroidTheme.DARK
                            expanded = false
                        },
                        modifier = Modifier.background(getBackgroundColor(wanAppViewModel = wanAppViewModel,
                            theme = WanandroidTheme.DARK)))
                    DropdownMenuItem(text = { Text(text = WanandroidTheme.LIGHT.named) },
                        onClick = {
                            wanAppViewModel.theme = WanandroidTheme.LIGHT
                            expanded = false
                        },
                        modifier = Modifier.background(getBackgroundColor(wanAppViewModel = wanAppViewModel,
                            theme = WanandroidTheme.LIGHT)))
                }
            }
            WanSettingsListItem(headlineText = "源代码",
                leadingIcon = R.drawable.ic_code,
                modifier = Modifier.clickable {
                    navHostController.navigate(Router.web + "/${Uri.encode(viewModel.github)}")
                },
                supportingText = { Text(text = viewModel.github) })
            WanSettingsListItem(headlineText = "关于",
                leadingIcon = R.drawable.ic_memory,
                modifier = Modifier.clickable {},
                supportingText = { Text(text = "version 1.0") })
            if (viewModel.isLogin) {
                FilledTonalButton(onClick = {
                    viewModel.userLogout()
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 24.dp)) {
                    Text(text = "退出登录")
                }
            }

        }
    }
}

@Composable
fun getBackgroundColor(wanAppViewModel: WanAppViewModel, theme: WanandroidTheme): Color {
    return if (wanAppViewModel.theme == theme) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
}

