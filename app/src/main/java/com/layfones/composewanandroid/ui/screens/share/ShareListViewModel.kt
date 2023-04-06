package com.layfones.composewanandroid.ui.screens.share

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layfones.composewanandroid.account.IAccountViewModelDelegate
import com.layfones.composewanandroid.data.repository.ShareListRepository
import com.layfones.composewanandroid.data.services.model.Article
import com.layfones.composewanandroid.data.services.model.CoinInfo
import com.layfones.composewanandroid.util.tryOffer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ShareListViewModel @Inject constructor(
    private val repository: ShareListRepository,
    private val accountViewModelDelegate: IAccountViewModelDelegate
) : ViewModel(), IAccountViewModelDelegate by accountViewModelDelegate {

    private val _fetchShare = Channel<String>(Channel.CONFLATED)

    fun fetch(userId: String) {
        _fetchShare.tryOffer(userId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val shareListFlow = _fetchShare.receiveAsFlow().flatMapLatest {
        repository.getShareList(it, it == userId)
    }.cachedIn(viewModelScope)

    val viewState by mutableStateOf(ShareListViewState(shareListFlow, repository.sharerCoinInfoFlow))

}

data class ShareListViewState(
    val pagingData: Flow<PagingData<Article>>,
    val sharerCoinInfo: Flow<CoinInfo>,
    val listState: LazyListState = LazyListState(),
)