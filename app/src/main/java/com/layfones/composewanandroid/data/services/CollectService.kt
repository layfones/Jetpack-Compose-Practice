package com.layfones.composewanandroid.data.services

import com.layfones.composewanandroid.common.http.adapter.NetworkResponse
import com.layfones.composewanandroid.data.services.model.CollectBean
import com.layfones.composewanandroid.data.services.model.PageResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface CollectService : BaseService {

    /**
     * 收藏列表
     */
    @GET("lg/collect/list/{page}/json")
    suspend fun getCollectList(@Path("page") page: Int): NetworkResponse<PageResponse<CollectBean>>

    /**
     * 收藏站内文章
     */
    @POST("lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Long): NetworkResponse<Any>

    /**
     * 取消收藏站内文章
     */
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollectArticle(@Path("id") id: Long): NetworkResponse<Any>

    suspend fun isCollectArticle(collect: Boolean, id: Long) =
        if (collect) collectArticle(id) else unCollectArticle(id)

}