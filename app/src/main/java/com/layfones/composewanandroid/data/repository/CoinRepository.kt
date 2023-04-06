package com.layfones.composewanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.layfones.composewanandroid.common.IntKeyPagingSource
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.CoinService
import javax.inject.Inject

class CoinRepository @Inject constructor(private val service: CoinService) {
    fun getCoinHistoryFlow() = Pager(
        PagingConfig(
            pageSize = BaseService.DEFAULT_PAGE_SIZE,
            initialLoadSize = BaseService.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        )
    ) {
        IntKeyPagingSource(service = service) { service, page, _ ->
            service.getMyCoinList(page).getOrNull()?.datas ?: emptyList()
        }
    }.flow
}