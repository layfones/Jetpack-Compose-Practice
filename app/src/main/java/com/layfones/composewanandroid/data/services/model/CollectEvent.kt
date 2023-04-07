package com.layfones.composewanandroid.data.services.model

/**
 * 收藏事件
 */
data class CollectEvent(
    val index: Int,
    val id: Int,
    val link: String,
    val isCollected: Boolean
)