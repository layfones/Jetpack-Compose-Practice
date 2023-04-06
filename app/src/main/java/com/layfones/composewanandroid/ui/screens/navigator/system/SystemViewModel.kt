package com.layfones.composewanandroid.ui.screens.navigator.system

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.repository.NavigatorRepository
import com.layfones.composewanandroid.data.services.model.Series
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SystemViewModel @Inject constructor(private val repository: NavigatorRepository) :
    ViewModel() {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val list: List<Series> = repository.getTreeList().getOrNull() ?: emptyList()
            viewState = viewState.copy(list = list)
        }
    }

    var viewState by mutableStateOf(SystemViewState())
        private set

}

data class SystemViewState(val list: List<Series> = emptyList(), val listState: LazyListState = LazyListState())