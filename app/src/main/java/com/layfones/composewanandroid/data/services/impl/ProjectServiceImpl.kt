package com.layfones.composewanandroid.data.services.impl

import com.layfones.composewanandroid.common.http.RetrofitManager
import com.layfones.composewanandroid.data.services.ProjectService
import javax.inject.Inject

class ProjectServiceImpl @Inject constructor() : ProjectService {

    private val service by lazy { RetrofitManager.getService(ProjectService::class.java) }

    override suspend fun getProjectTitleList() = service.getProjectTitleList()

    override suspend fun getProjectPageList(pageNo: Int, pageSize: Int, categoryId: Int) =
        service.getProjectPageList(pageNo, pageSize, categoryId)

    override suspend fun getNewProjectPageList(pageNo: Int, pageSize: Int) =
        service.getNewProjectPageList(pageNo, pageSize)

}