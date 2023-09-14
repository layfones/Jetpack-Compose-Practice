package com.layfones.composewanandroid.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.layfones.composewanandroid.data.services.model.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    fun getAll():PagingSource<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)

    @Query("DELETE FROM Article")
    fun removeAll()

}