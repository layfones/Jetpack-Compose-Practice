package com.layfones.composewanandroid.ui.screens.home.explore

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layfones.composewanandroid.data.repository.DatabaseRepository
import com.layfones.composewanandroid.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val repository: HomeRepository, private val databaseRepository: DatabaseRepository) : ViewModel() {

    /**
     * 首页列表数据Flow
     */
    private val articlesFlow by lazy {
        repository.getArticlePageList(20).cachedIn(viewModelScope)
    }

    var viewState by mutableStateOf(ExploreViewState(collecting = false,listState = LazyListState(), pagingDataFlow = articlesFlow))
        private set


}

data class ExploreViewState(
    val collecting: Boolean,
    val listState: LazyListState,
    val pagingDataFlow: Flow<PagingData<Any>>
)