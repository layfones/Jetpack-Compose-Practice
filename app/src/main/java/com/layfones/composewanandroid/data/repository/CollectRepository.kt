package com.layfones.composewanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.layfones.composewanandroid.common.IntKeyPagingSource
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.CollectService
import com.layfones.composewanandroid.data.services.model.CollectEvent
import javax.inject.Inject

class CollectRepository @Inject constructor(private val service: CollectService) {

    fun getCollectFlow() = Pager(PagingConfig(pageSize = BaseService.DEFAULT_PAGE_SIZE,
        initialLoadSize = BaseService.DEFAULT_PAGE_SIZE,
        enablePlaceholders = false)) {
        IntKeyPagingSource(
            BaseService.DEFAULT_PAGE_START_NO,
            service = service
        ) { profileService, page, _ ->
            profileService.getCollectList(page).getOrNull()?.datas ?: emptyList()
        }
    }.flow

    suspend fun articleCollectAction(event: CollectEvent) =
        service.isCollectArticle(event.isCollected, event.id)

}