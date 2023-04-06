package com.layfones.composewanandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 1)
abstract class WanDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}