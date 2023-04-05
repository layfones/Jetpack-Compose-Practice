package com.layfones.composewanandroid.ui

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.layfones.composewanandroid.WanandroidApp
import com.layfones.composewanandroid.ui.theme.WanandroidTheme

class WanAppState constructor(application: Application) : AndroidViewModel(application) {
    var theme by mutableStateOf(WanandroidTheme.LIGHT)
}

@Composable
fun createAppStateViewModel(): WanAppState {
    val factory = remember {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return WanAppState(WanandroidApp.app) as T
            }
        }
    }
    return viewModel(viewModelStoreOwner = WanandroidApp.app, factory = factory)
}