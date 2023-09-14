package com.layfones.composewanandroid.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.Room
import com.layfones.composewanandroid.App
import com.layfones.composewanandroid.common.IntKeyPagingSource
import com.layfones.composewanandroid.common.http.adapter.getOrElse
import com.layfones.composewanandroid.common.http.adapter.getOrNull
import com.layfones.composewanandroid.data.database.WanDatabase
import com.layfones.composewanandroid.data.services.BaseService
import com.layfones.composewanandroid.data.services.HomeService
import com.layfones.composewanandroid.data.services.model.Article
import com.layfones.composewanandroid.data.services.model.Banners
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class HomeRepository @Inject constructor(private val service: HomeService) {

    private var database: WanDatabase = Room.databaseBuilder(App.app,
        WanDatabase::class.java,
        "app_database")
        .allowMainThreadQueries()
        .build()

    private suspend fun getBanner() = service.getBanner()

    private suspend fun getArticleTopList() = service.getArticleTopList()

    fun getArticlePageList(pageSize: Int) = Pager(
        config =  PagingConfig(
            pageSize = pageSize,
            initialLoadSize = pageSize,
            enablePlaceholders = false
        )
    ) {
        IntKeyPagingSource(BaseService.DEFAULT_PAGE_START_NO, service) { service, page, size ->
            // 根据Page来区分是否需要拉取Banner和置顶文章
            if (page == BaseService.DEFAULT_PAGE_START_NO) {
                // 使用async并行请求
                val (articlesDeferred, topsDeferred, bannersDeferred) =
                    supervisorScope {
                        Triple(
                            async { service.getArticlePageList(page, size) },
                            async { getArticleTopList() },
                            async { getBanner() }
                        )
                    }
                val articles = articlesDeferred.await().getOrNull()?.datas ?: emptyList()
                val tops = topsDeferred.await().getOrElse { emptyList() }
                val banners = bannersDeferred.await().getOrElse { emptyList() }
                with(ArrayList<Any>(1 + tops.size + articles.size)) {
                    if (banners.isNotEmpty()) {
                        add(Banners(banners))
                    }
                    addAll(tops)
                    addAll(articles)
                    this
                }
            } else {
                // page不为0则只是加载更多即可
                service.getArticlePageList(page, size).getOrNull()?.datas ?: emptyList()
            }
        }
    }.flow

    fun getSquarePageList(pageSize: Int) = Pager(
        PagingConfig(
            pageSize = pageSize, initialLoadSize = pageSize, enablePlaceholders = false
        )
    ) {
        IntKeyPagingSource(
            BaseService.DEFAULT_PAGE_START_NO,
            service = service
        ) { service, page, size ->
            service.getSquarePageList(page, size).getOrNull()?.datas ?: emptyList()
        }
    }.flow

    @OptIn(ExperimentalPagingApi::class)
    fun getSquarePageList2(pageSize: Int) = Pager(
        config =  PagingConfig(
            pageSize = pageSize,
            initialLoadSize = pageSize,
            enablePlaceholders = false
        ), remoteMediator = HomeRemoteMediator(service, database)
    ) {
        database.articleDao().getAll()
    }.flow



    fun getAnswerPageList() =
        Pager(
            PagingConfig(
                pageSize = BaseService.DEFAULT_PAGE_SIZE,
                initialLoadSize = BaseService.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            IntKeyPagingSource(
                BaseService.DEFAULT_PAGE_START_NO_1,
                service = service
            ) { service, page, _ ->
                service.getAnswerPageList(page).getOrNull()?.datas ?: emptyList()
            }
        }.flow

}