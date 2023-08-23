package com.layfones.composewanandroid.ui.screens.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.repository.ProjectRepository
import com.layfones.composewanandroid.data.services.model.ProjectTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(private val repository: ProjectRepository) :
    ViewModel() {

    init {
        getProjectTitle()
    }

    fun getProjectTitle() {
        viewModelScope.launch {
            val titleList = repository.getProjectTitleList()
            val titles = titleList.getOrNull() ?: emptyList()
            viewState = viewState.copy(titleData = titles)
        }
    }

    var viewState by mutableStateOf(ProjectViewState(titleData = emptyList()))
        private set
}

data class ProjectViewState(
    val titleData: List<ProjectTitle>,
)