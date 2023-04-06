package com.layfones.composewanandroid.data.services

import com.layfones.composewanandroid.common.http.adapter.NetworkResponse
import com.layfones.composewanandroid.data.services.model.Article
import com.layfones.composewanandroid.data.services.model.PageResponse
import com.layfones.composewanandroid.data.services.model.ProjectTitle
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProjectService : BaseService {

    /**
     * 项目分类数据
     */
    @GET("project/tree/json")
    suspend fun getProjectTitleList(): NetworkResponse<List<ProjectTitle>>

    /**
     * 项目文章列表
     */
    @GET("project/list/{pageNo}/json")
    suspend fun getProjectPageList(
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int,
        @Query("cid") categoryId: Int
    ): NetworkResponse<PageResponse<Article>>

    /**
     * 最新项目列表
     */
    @GET("article/listproject/{pageNo}/json")
    suspend fun getNewProjectPageList(
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int
    ): NetworkResponse<PageResponse<Article>>
}