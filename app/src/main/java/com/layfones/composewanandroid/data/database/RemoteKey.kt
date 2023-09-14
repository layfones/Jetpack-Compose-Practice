package com.layfones.composewanandroid.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Leizf
 * @date 2023/9/14
 * @desc
 */
@Entity
data class RemoteKey(@PrimaryKey val remoteName: String, val nextKey: Int?)