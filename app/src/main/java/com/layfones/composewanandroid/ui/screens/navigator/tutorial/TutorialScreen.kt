package com.layfones.composewanandroid.ui.screens.navigator.tutorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.layfones.composewanandroid.ui.components.WanStatePage
import com.layfones.composewanandroid.ui.components.WanTutorialItem

@Composable
fun TutorialScreen(viewModel: TutorialViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState
    WanStatePage(loading = viewState.list.isEmpty(), viewState.list.isEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = viewState.listState) {
            itemsIndexed(viewState.list) { _, value ->
                WanTutorialItem(value)
            }
        }
    }

}