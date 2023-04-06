package com.layfones.composewanandroid.data.repository

import androidx.room.Room
import com.layfones.composewanandroid.App
import com.layfones.composewanandroid.data.database.ArticleDao
import com.layfones.composewanandroid.data.database.WanDatabase
import com.layfones.composewanandroid.data.services.HomeService
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val service: HomeService) {

    private var db:WanDatabase = Room.inMemoryDatabaseBuilder(App.app.applicationContext, WanDatabase::class.java).allowMainThreadQueries().build()
    var articleDao: ArticleDao = db.articleDao()

//    @OptIn(ExperimentalPagingApi::class)
//    fun getHome(pageSize: Int) = Pager(
//        PagingConfig(
//        pageSize = pageSize,
//        initialLoadSize = pageSize,
//        enablePlaceholders = false
//    ), remoteMediator = object :RemoteMediator<Int, Article>(){
//            override suspend fun load(
//                loadType: LoadType,
//                state: PagingState<Int, Article>
//            ): MediatorResult {
//                val page:Int = when (loadType) {
//                    LoadType.REFRESH -> 0
//                    LoadType.PREPEND -> return MediatorResult.Success(true)
//                    LoadType.APPEND -> 0
//                }
//                val articlePageList = service.getArticlePageList(page, pageSize)
//                val articles = articlePageList.getOrNull()?.datas ?: emptyList<com.layfones.composewanandroid.data.services.model.Article>().map {
//                    Article()
//                }
//                articleDao.insert(articles)
//            }
//        }
//    ) {
//        articleDao.getAll()
//    }.flow

}