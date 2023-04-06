package com.layfones.composewanandroid.ui

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.layfones.composewanandroid.App
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.repository.CollectRepository
import com.layfones.composewanandroid.data.services.impl.CollectServiceImpl
import com.layfones.composewanandroid.data.services.model.CollectEvent
import com.layfones.composewanandroid.data.usecase.ArticleCollectUseCase
import com.layfones.composewanandroid.ui.theme.WanandroidTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WanAppViewModel constructor(application: Application, private val articleCollectUseCase: ArticleCollectUseCase) : AndroidViewModel(application) {

    var theme by mutableStateOf(WanandroidTheme.LIGHT)

    private val _collectFlow = MutableSharedFlow<CollectEvent>()
    val collectFlow = _collectFlow.asSharedFlow()
    /**
     * 收藏文章
     */
    fun articleCollectAction(event: CollectEvent) {
        viewModelScope.launch {
            articleCollectUseCase.articleCollectAction(event).getOrNull() ?: return@launch
            _collectFlow.emit(event)
        }
    }
}

@Composable
fun createAppViewModel(): WanAppViewModel {
    val factory = remember {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return WanAppViewModel(App.app, ArticleCollectUseCase(CollectRepository(CollectServiceImpl()))) as T
            }
        }
    }
    return viewModel(viewModelStoreOwner = App.app, factory = factory)
}