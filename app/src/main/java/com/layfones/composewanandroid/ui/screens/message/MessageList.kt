package com.layfones.composewanandroid.ui.screens.message

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.layfones.composewanandroid.data.services.model.MsgBean
import com.layfones.composewanandroid.ui.components.WanMessageItem
import com.layfones.composewanandroid.ui.components.WanStatePage

@Composable
fun MessageList(index: Int, viewModel: MessageListViewModel = hiltViewModel()) {

    val messageFlow: LazyPagingItems<MsgBean> = if (index == 0) {
        viewModel.getUnreadMsgFlow.collectAsLazyPagingItems()
    } else {
        viewModel.getReadiedMsgFlow.collectAsLazyPagingItems()
    }

    WanStatePage(
        loading = messageFlow.loadState.refresh is LoadState.Loading,
        empty = messageFlow.itemCount == 0
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(messageFlow.itemCount, key = messageFlow.itemKey { it.id }) {index ->
                WanMessageItem(message = messageFlow[index]!!, modifier = Modifier)
            }
        }
    }

}