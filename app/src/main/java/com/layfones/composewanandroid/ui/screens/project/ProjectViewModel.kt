package com.layfones.composewanandroid.ui.screens.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layfones.composewanandroid.base.http.adapter.getOrNull
import com.layfones.composewanandroid.data.repository.ProjectRepository
import com.layfones.composewanandroid.data.services.model.ProjectTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(private val repository: ProjectRepository) :
    ViewModel() {

    fun getProjectTitle() {
        viewModelScope.launch {
            val titleList = repository.getProjectTitleList()
            val titles = titleList.getOrNull() ?: emptyList()
            viewState = viewState.copy(titleData = titles)
        }
    }

    // fun getProjectList(categoryId: Int) {
    //     viewModelScope.launch {
    //         val projectListFlow: Flow<PagingData<Article>> = repository.getProjectListFlow(BaseService.DEFAULT_PAGE_SIZE, categoryId)
    //         viewState = viewState.copy(projectList = projectListFlow)
    //     }
    // }

    var viewState by mutableStateOf(ProjectViewState(titleData = emptyList()))
}

data class ProjectViewState(
    val titleData: List<ProjectTitle>,
)