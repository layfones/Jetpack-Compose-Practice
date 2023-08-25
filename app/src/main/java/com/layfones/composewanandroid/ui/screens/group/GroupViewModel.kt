package com.layfones.composewanandroid.ui.screens.group

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.repository.GroupRepository
import com.layfones.composewanandroid.data.services.model.Classify
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(private val repository: GroupRepository) : ViewModel() {

    init {
        getGroupTitle()
    }

    private fun getGroupTitle() {
        viewModelScope.launch {
            val titleList = repository.getAuthorTitleList()
            val titles = titleList.getOrNull() ?: emptyList()
            viewState = viewState.copy(titleData = titles)
        }
    }

    var viewState by mutableStateOf(GroupViewState(titleData = emptyList()))
}

data class GroupViewState(val titleData: List<Classify>)