package com.layfones.composewanandroid.ui.screens.home.answer

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layfones.composewanandroid.data.repository.HomeRepository
import com.layfones.composewanandroid.data.services.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AnswerViewModel @Inject constructor(private val repository: HomeRepository):ViewModel() {

    private val getAnswerFlow by lazy {
        repository.getAnswerPageList().cachedIn(viewModelScope)
    }

    val viewState by mutableStateOf(AnswerViewState(pagingData = getAnswerFlow))

}

data class AnswerViewState(
    val listState: LazyListState = LazyListState(),
    val pagingData: Flow<PagingData<Article>>
)