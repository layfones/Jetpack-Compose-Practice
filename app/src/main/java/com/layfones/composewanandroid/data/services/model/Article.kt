package com.layfones.composewanandroid.data.services.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.layfones.composewanandroid.data.database.TagConvertor

/**
 * 文章
 */
@Entity
@TypeConverters(TagConvertor::class)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val dbId:Int = 0,
    var id: Int,
    var apkLink: String,
    var audit: Int,
    var author: String,
    var canEdit: Boolean,
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Int,
    var desc: String,
    var descMd: String,
    var envelopePic: String,
    var fresh: Boolean,
    var host: String,
    var link: String,
    var niceDate: String,
    var niceShareDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var realSuperChapterId: Int,
    var selfVisible: Int,
    var shareDate: Long,
    var shareUser: String,
    var superChapterId: Int,
    var superChapterName: String,
    var tags: List<Tag>,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int
){

    /**
     * 获取文章作者
     */
    fun getArticleAuthor(): String = author.ifEmpty { shareUser }

}