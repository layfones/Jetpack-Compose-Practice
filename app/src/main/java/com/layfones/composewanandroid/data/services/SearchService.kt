package com.layfones.composewanandroid.data.services

import com.layfones.composewanandroid.base.http.adapter.NetworkResponse
import com.layfones.composewanandroid.data.services.model.Article
import com.layfones.composewanandroid.data.services.model.HotKeyBean
import com.layfones.composewanandroid.data.services.model.PageResponse
import retrofit2.http.*


interface SearchService : BaseService {

    /**
     * 热搜词
     */
    @GET("hotkey/json")
    suspend fun getSearchHotKey(): NetworkResponse<List<HotKeyBean>>

    /**
     * 搜索
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun queryBySearchKey(
        @Path("page") page: Int,
        @Field("k") key: String
    ): NetworkResponse<PageResponse<Article>>
}