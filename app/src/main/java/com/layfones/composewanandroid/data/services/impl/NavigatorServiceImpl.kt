package com.layfones.composewanandroid.data.services.impl

import com.layfones.composewanandroid.common.http.RetrofitManager
import com.layfones.composewanandroid.data.services.NavigatorService
import javax.inject.Inject

class NavigatorServiceImpl @Inject constructor() : NavigatorService {

    private val service by lazy { RetrofitManager.getService(NavigatorService::class.java) }

    override suspend fun getNavigationList() = service.getNavigationList()

    override suspend fun getTreeList() = service.getTreeList()

    override suspend fun getTutorialList() = service.getTutorialList()

    override suspend fun getTutorialChapterList(id: Int, orderType: Int) =
        service.getTutorialChapterList(id, orderType)

    override suspend fun getSeriesDetailList(page: Int, id: Int, size: Int) =
        service.getSeriesDetailList(page, id, size)
}