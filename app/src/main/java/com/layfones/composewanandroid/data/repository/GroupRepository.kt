package com.layfones.composewanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.layfones.composewanandroid.common.IntKeyPagingSource
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.GroupService
import javax.inject.Inject

class GroupRepository @Inject constructor(private val service: GroupService) {

    suspend fun getAuthorTitleList() = service.getAuthorTitleList()

    fun getAuthorArticles(id: Int) = Pager(
        PagingConfig(
            pageSize = BaseService.DEFAULT_PAGE_SIZE,
            initialLoadSize = BaseService.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        )
    ) {
        IntKeyPagingSource(service = service) { service, page, size ->
            service.getAuthorArticles(id, page = page, size).getOrNull()?.datas ?: emptyList()
        }
    }.flow

}