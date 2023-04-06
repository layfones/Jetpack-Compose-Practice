package com.layfones.composewanandroid.ui.screens.coin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.ui.components.BackButton
import com.layfones.composewanandroid.ui.components.StatePage

@Composable
fun CoinScreen(viewModel: CoinViewModel = hiltViewModel()) {
    val navHostController = LocalNavController.current
    val viewState = viewModel.viewState
    val userBaseInfoState = viewState.accountInfo.collectAsState()
    val data = viewState.pagingData.collectAsLazyPagingItems()
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            BackButton {
                navHostController.popBackStack()
            }
            androidx.compose.material.Text(
                text = "我的积分",
                Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }

        StatePage(
            loading = data.loadState.refresh is LoadState.Loading,
            empty = data.itemCount == 0
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = userBaseInfoState.value.coinInfo.coinCount.toString(),
                            style = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.primary)
                        )
                        Text(
                            text = "等级: " + userBaseInfoState.value.coinInfo.level,
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
                        )
                        Text(
                            text = "排名: " + userBaseInfoState.value.coinInfo.rank,
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
                        )
                    }
                }

                itemsIndexed(data) { _, value ->
                    val coinHistory = value!!
                    ListItem(
                        trailingContent = {
                            Text(
                                text = "${if (coinHistory.coinCount > 0) "+" + coinHistory.coinCount else coinHistory.coinCount}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        headlineText = {
                            Text(text = coinHistory.reason)
                        },
                        supportingText = { Text(text = coinHistory.desc) }
                    )
                }
            }
        }
    }
}