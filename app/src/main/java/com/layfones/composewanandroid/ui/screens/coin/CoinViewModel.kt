package com.layfones.composewanandroid.ui.screens.coin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.layfones.composewanandroid.account.IAccountViewModelDelegate
import com.layfones.composewanandroid.data.repository.CoinRepository
import com.layfones.composewanandroid.data.services.model.CoinHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    repository: CoinRepository,
    accountViewModelDelegate: IAccountViewModelDelegate
) : ViewModel(), IAccountViewModelDelegate by accountViewModelDelegate {

    val userBaseInfoFlow = accountViewModelDelegate.accountInfo

    val coinHistoryFlow = repository.getCoinHistoryFlow().cachedIn(viewModelScope)

    val viewState by mutableStateOf(CoinViewState(coinHistoryFlow))
}

data class CoinViewState(val pagingData: Flow<PagingData<CoinHistory>>)