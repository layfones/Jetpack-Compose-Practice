package com.layfones.composewanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.layfones.composewanandroid.base.IntKeyPagingSource
import com.layfones.composewanandroid.base.http.adapter.getOrNull
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.ProfileService
import javax.inject.Inject

class MessageRepository @Inject constructor(private val service: ProfileService) {


    /**
     * 已读消息Flow
     */
    fun getReadiedMsgFlow() = Pager(
        PagingConfig(
            pageSize = BaseService.DEFAULT_PAGE_SIZE,
            initialLoadSize = BaseService.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        )
    ) {
        IntKeyPagingSource(service = service) { service, page, _ ->
            service.getReadiedMessageList(page).getOrNull()?.datas ?: emptyList()
        }
    }.flow

    /**
     * 未读消息Flow
     */
    fun getUnreadMsgFlow() = Pager(
        PagingConfig(
            pageSize = BaseService.DEFAULT_PAGE_SIZE,
            initialLoadSize = BaseService.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        )
    ) {
        IntKeyPagingSource(service = service) { service, page, _ ->
            service.getUnReadMessageList(page).getOrNull()?.datas ?: emptyList()
        }
    }.flow
}