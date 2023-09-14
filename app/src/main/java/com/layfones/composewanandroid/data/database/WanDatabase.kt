package com.layfones.composewanandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.layfones.composewanandroid.data.services.model.Article

@Database(entities = [Article::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class WanDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun remoteKeyDao(): RemoteKeyDao

}