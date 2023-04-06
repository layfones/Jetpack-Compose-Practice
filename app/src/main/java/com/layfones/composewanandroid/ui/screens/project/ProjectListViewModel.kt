package com.layfones.composewanandroid.ui.screens.project

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layfones.composewanandroid.data.repository.ProjectRepository
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.impl.ProjectServiceImpl
import com.layfones.composewanandroid.data.services.model.Article
import kotlinx.coroutines.flow.Flow


class ProjectListViewModel(val id: Int, val repository: ProjectRepository) : ViewModel() {

    private val pagingDataFlow by lazy {
        repository.getProjectListFlow(BaseService.DEFAULT_PAGE_SIZE, id).cachedIn(viewModelScope)
    }

    var viewState by mutableStateOf(ProjectListViewState(projectList = pagingDataFlow))
        private set
}

@Immutable
data class ProjectListViewState(
    val listState: LazyListState = LazyListState(),
    val projectList: Flow<PagingData<Article>>
)

@Composable
inline fun <reified VM : ViewModel> createProjectListViewModel(id: Int): VM {
    val owner = LocalViewModelStoreOwner.current
    val defaultExtras = remember {
        (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelCreationExtras
            ?: CreationExtras.Empty
    }
    val extraKey = remember {
        object : CreationExtras.Key<Int> {}
    }
    val extras = remember {
        MutableCreationExtras(defaultExtras).apply { set(extraKey, id) }
    }
    val factory = remember {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val pid = extras[extraKey]!!
                @Suppress("UNCHECKED_CAST")
                return ProjectListViewModel(pid, ProjectRepository(ProjectServiceImpl())) as T
            }
        }
    }
    return viewModel(owner!!, id.toString(), factory, extras)
}