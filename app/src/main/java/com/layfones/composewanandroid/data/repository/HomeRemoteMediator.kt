package com.layfones.composewanandroid.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.layfones.composewanandroid.common.http.adapter.getOrThrow
import com.layfones.composewanandroid.data.database.RemoteKey
import com.layfones.composewanandroid.data.database.WanDatabase
import com.layfones.composewanandroid.data.services.HomeService
import com.layfones.composewanandroid.data.services.model.Article

/**
 * @author Leizf
 * @date 2023/9/14
 * @desc
 */
@OptIn(ExperimentalPagingApi::class)
class HomeRemoteMediator(private val service: HomeService, private val database: WanDatabase) :
    RemoteMediator<Int, Article>() {


    override suspend fun load(loadType: LoadType,
                              state: PagingState<Int, Article>): MediatorResult {
        try {
            val articleDao = database.articleDao()
            val remoteKeyDao = database.remoteKeyDao()
            val nextKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = remoteKeyDao.getRemoteKey("Home") ?: return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKey.nextKey
                }
            }
            val page =nextKey ?: 0
            val response = service.getSquarePageList(page, 20)
            val articleList = response.getOrThrow().datas
            val endOfPaginationReached = articleList.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.removeAll()
                    remoteKeyDao.removeAll()
                }
                val nextPageKey = if (endOfPaginationReached) null else page + 1
                remoteKeyDao.insert(RemoteKey("Home", nextPageKey))
                articleDao.insertAll(articleList)
                // articleList.forEach {
                //     it.insertTime = System.currentTimeMillis()
                //     articleDao.insert(it)
                // }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

}