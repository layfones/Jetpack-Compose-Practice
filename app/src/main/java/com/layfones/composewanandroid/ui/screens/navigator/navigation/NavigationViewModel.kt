package com.layfones.composewanandroid.ui.screens.navigator.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layfones.composewanandroid.base.http.adapter.getOrNull
import com.layfones.composewanandroid.data.repository.NavigatorRepository
import com.layfones.composewanandroid.data.services.model.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(private val repository: NavigatorRepository) :
    ViewModel() {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val list = repository.getNavigationList().getOrNull() ?: emptyList()
            viewState = viewState.copy(dataList = list)
        }
    }

    var viewState by mutableStateOf(NavigationViewState())
        private set
}

data class NavigationViewState(
    val dataList: List<Navigation> = emptyList()
)