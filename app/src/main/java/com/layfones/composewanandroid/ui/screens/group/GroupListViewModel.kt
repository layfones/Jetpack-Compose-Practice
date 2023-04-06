package com.layfones.composewanandroid.ui.screens.group

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
import com.layfones.composewanandroid.data.repository.GroupRepository
import com.layfones.composewanandroid.data.services.impl.GroupServiceImpl
import com.layfones.composewanandroid.data.services.model.Article
import kotlinx.coroutines.flow.Flow

class GroupListViewModel(val id: Int, val repository: GroupRepository) : ViewModel() {

    private val pagingDataFlow by lazy {
        repository.getAuthorArticles(id).cachedIn(viewModelScope)
    }

    var viewState by mutableStateOf(GroupListViewState(pagingDataFlow, LazyListState()))
        private set

}

data class GroupListViewState(
    val projectList: Flow<PagingData<Article>>,
    val listState: LazyListState
)

@Composable
inline fun <reified VM : ViewModel> createGroupListViewModel(id: Int): VM {
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
                return GroupListViewModel(pid, GroupRepository(GroupServiceImpl())) as T
            }
        }
    }
    return viewModel(owner!!, id.toString(), factory, extras)
}