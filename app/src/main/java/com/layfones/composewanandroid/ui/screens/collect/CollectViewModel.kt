package com.layfones.composewanandroid.ui.screens.collect

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layfones.composewanandroid.data.repository.CollectRepository
import com.layfones.composewanandroid.data.services.model.Article
import com.layfones.composewanandroid.data.services.model.CollectBean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CollectViewModel @Inject constructor(repository: CollectRepository) : ViewModel() {

    private val getCollectFlow by lazy {
        repository.getCollectFlow().cachedIn(viewModelScope)
    }

    val viewState by mutableStateOf(CollectViewState(pagingData = getCollectFlow))
}

data class CollectViewState(
    val pagingData: Flow<PagingData<CollectBean>>
)