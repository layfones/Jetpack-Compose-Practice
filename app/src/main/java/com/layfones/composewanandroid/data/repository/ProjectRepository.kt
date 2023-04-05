package com.layfones.composewanandroid.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.layfones.composewanandroid.base.IntKeyPagingSource
import com.layfones.composewanandroid.base.http.adapter.getOrNull
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.ProjectService
import com.layfones.composewanandroid.data.services.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepository @Inject constructor(private val service: ProjectService) {

    suspend fun getProjectTitleList() = service.getProjectTitleList()

    /**
     * 项目列表Flow
     */
    fun getProjectListFlow(pageSize: Int, categoryId: Int): Flow<PagingData<Article>> {
        Log.d("项目列表Flow:::", "pageSize: "+pageSize+"        categoryId:"+categoryId)
        return Pager(
            PagingConfig(
                pageSize = pageSize,
                initialLoadSize = pageSize,
                enablePlaceholders = false
            )
        ) {
            IntKeyPagingSource(service = service) { service, page, size ->
                service.getProjectPageList(page, size, categoryId).getOrNull()?.datas ?: emptyList()
            }
        }.flow
    }

    /**
     * 最新项目列表Flow
     */
    fun getNewProjectListFlow(pageSize: Int) = Pager(
        PagingConfig(pageSize = pageSize, initialLoadSize = pageSize, enablePlaceholders = false)
    ) {
        IntKeyPagingSource(BaseService.DEFAULT_PAGE_START_NO, service) { service, page, size ->
            service.getNewProjectPageList(page, size).getOrNull()?.datas ?: emptyList()
        }
    }.flow
}