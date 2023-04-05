package com.layfones.composewanandroid.ui.screens.home.explore

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layfones.composewanandroid.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {


    /**
     * 首页列表数据Flow
     */
     val getArticlesFlow by lazy {
        repository.getArticlePageList(20).cachedIn(viewModelScope)
    }

    var viewState by mutableStateOf(ExploreViewState(pagingData = getArticlesFlow))
        private set

    fun getData() {
        viewState = viewState.copy(isRefreshing = true)
        val articlePageList = repository.getArticlePageList(20)
    }

}

data class ExploreViewState(
    val isRefreshing: Boolean = false,
    val listState: LazyListState = LazyListState(),
    val pagingData: Flow<PagingData<Any>>
)

sealed class ExploreViewAction {
    object FetchData : ExploreViewAction()
}