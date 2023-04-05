package com.layfones.composewanandroid.data.services.impl

import com.layfones.composewanandroid.base.http.RetrofitManager
import com.layfones.composewanandroid.data.services.CollectService
import javax.inject.Inject

class CollectServiceImpl @Inject constructor() : CollectService {

    private val service by lazy { RetrofitManager.getService(CollectService::class.java) }

    override suspend fun getCollectList(page: Int) = service.getCollectList(page)

    override suspend fun collectArticle(id: Int) = service.collectArticle(id)

    override suspend fun unCollectArticle(id: Int) = service.unCollectArticle(id)

}