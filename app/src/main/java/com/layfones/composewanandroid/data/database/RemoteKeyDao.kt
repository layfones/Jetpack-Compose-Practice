package com.layfones.composewanandroid.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Leizf
 * @date 2023/9/14
 * @desc
 */
@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(remoteKey: RemoteKey)

    @Query("SELECT * FROM RemoteKey WHERE remoteName = :name")
    fun getRemoteKey(name: String):RemoteKey?

    @Query("DELETE FROM RemoteKey")
    fun removeAll()

}