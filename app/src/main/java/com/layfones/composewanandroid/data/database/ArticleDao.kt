package com.layfones.composewanandroid.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.layfones.composewanandroid.data.services.model.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    fun getAll():PagingSource<Int, Article>

    @Insert
    fun insert(articles: List<Article>)

}