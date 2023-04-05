package com.layfones.composewanandroid.data.services.impl

import com.layfones.composewanandroid.base.http.RetrofitManager
import com.layfones.composewanandroid.data.services.HomeService
import javax.inject.Inject

class HomeServiceImpl @Inject constructor() : HomeService {

    private val service by lazy { RetrofitManager.getService(HomeService::class.java) }

    override suspend fun getBanner() = service.getBanner()

    override suspend fun getArticleTopList() = service.getArticleTopList()

    override suspend fun getArticlePageList(pageNo: Int, pageSize: Int) =
        service.getArticlePageList(pageNo, pageSize)

    override suspend fun getSquarePageList(pageNo: Int, pageSize: Int) =
        service.getSquarePageList(pageNo, pageSize)

    override suspend fun getAnswerPageList(pageNo: Int) = service.getAnswerPageList(pageNo)
}