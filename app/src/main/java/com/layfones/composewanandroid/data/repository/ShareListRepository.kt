package com.layfones.composewanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.layfones.composewanandroid.common.IntKeyPagingSource
import com.layfones.composewanandroid.common.http.adapter.NetworkResponse
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.common.http.adapter.whenSuccess
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.ProfileService
import com.layfones.composewanandroid.data.services.model.CoinInfo
import com.layfones.composewanandroid.data.services.model.ShareBean
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class ShareListRepository @Inject constructor(private val profileService: ProfileService) {

    private val _sharerCoinInfoFlow = MutableSharedFlow<CoinInfo>(1)
    val sharerCoinInfoFlow: SharedFlow<CoinInfo> = _sharerCoinInfoFlow

    fun getShareList(userId: String, isMe: Boolean) = Pager(
        PagingConfig(
            pageSize = BaseService.DEFAULT_PAGE_SIZE,
            initialLoadSize = BaseService.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        )
    ) {
        IntKeyPagingSource(service = profileService) { profileService, page, _ ->
            val result: NetworkResponse<ShareBean> = if (isMe) {
                profileService.getMyShareList(page)
            } else {
                profileService.getUserShareList(userId, page)
            }
            result.whenSuccess { _sharerCoinInfoFlow.emit(it.coinInfo) }
            result.getOrNull()?.shareArticles?.datas ?: emptyList()
        }
    }.flow

}