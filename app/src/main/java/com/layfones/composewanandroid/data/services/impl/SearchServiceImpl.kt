package com.layfones.composewanandroid.data.services.impl

import com.layfones.composewanandroid.common.http.RetrofitManager
import com.layfones.composewanandroid.data.services.SearchService
import javax.inject.Inject

class SearchServiceImpl @Inject constructor() : SearchService {

    private val service by lazy { RetrofitManager.getService(SearchService::class.java) }

    override suspend fun getSearchHotKey() = service.getSearchHotKey()

    override suspend fun queryBySearchKey(page: Int, key: String) =
        service.queryBySearchKey(page, key)
}