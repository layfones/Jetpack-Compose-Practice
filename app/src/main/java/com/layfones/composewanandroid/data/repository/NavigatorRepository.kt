package com.layfones.composewanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.layfones.composewanandroid.common.IntKeyPagingSource
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.NavigatorService
import javax.inject.Inject

class NavigatorRepository @Inject constructor(private val service: NavigatorService) {

    suspend fun getNavigationList() = service.getNavigationList()

    suspend fun getTreeList() = service.getTreeList()

    suspend fun getTutorialList() = service.getTutorialList()

    suspend fun getTutorialChapterList(id: Int, orderType: Int = 1) =
        service.getTutorialChapterList(id, orderType)

    fun getSystemDetailList(id: Int, size: Int) = Pager(
        PagingConfig(pageSize = size, initialLoadSize = size, enablePlaceholders = false)
    ) {
        IntKeyPagingSource(BaseService.DEFAULT_PAGE_START_NO, service) { service, page, size ->
            service.getSeriesDetailList(page, id, size).getOrNull()?.datas ?: emptyList()
        }
    }.flow

}