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
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.HomeService
import com.layfones.composewanandroid.data.services.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SquareViewModel @Inject constructor(val service: HomeService) : ViewModel() {

    private val pagingDataFlow by lazy {
        Pager(PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false)) {
            IntKeyPagingSource(
                BaseService.DEFAULT_PAGE_START_NO,
                service = service
            ) { service, page, size ->
                service.getSquarePageList(page, size).getOrNull()?.datas ?: emptyList()
            }
        }.flow.cachedIn(viewModelScope)
    }

    var viewState by mutableStateOf(SquareViewState(pagingData = pagingDataFlow))
        private set
}

data class SquareViewState(
    val listState: LazyListState = LazyListState(),
    val pagingData: Flow<PagingData<Article>>
)
