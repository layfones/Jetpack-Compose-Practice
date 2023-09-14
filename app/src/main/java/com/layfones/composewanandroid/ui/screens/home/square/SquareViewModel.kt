package com.layfones.composewanandroid.ui.screens.home.square

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layfones.composewanandroid.common.IntKeyPagingSource
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.repository.HomeRepository
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.HomeService
import com.layfones.composewanandroid.data.services.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SquareViewModel @Inject constructor(val repository: HomeRepository) : ViewModel() {

    var viewState by mutableStateOf(SquareViewState(pagingData = repository.getSquarePageList(20).cachedIn(viewModelScope)))
        private set
}

data class SquareViewState(
    val listState: LazyListState = LazyListState(),
    val pagingData: Flow<PagingData<Article>>
)
