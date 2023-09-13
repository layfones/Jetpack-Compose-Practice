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
    var id: Long,
    var apkLink: String,
    var audit: Int,
    var author: String,
    var canEdit: Boolean,
    var chapterId: Long,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Long,
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
    var realSuperChapterId: Long,
    var selfVisible: Int,
    var shareDate: Long,
    var shareUser: String,
    var superChapterId: Long,
    var superChapterName: String,
    var tags: List<Tag>,
    var title: String,
    var type: Int,
    var userId: Long,
    var visible: Int,
    var zan: Int
){

    /**
     * 获取文章作者
     */
    fun getArticleAuthor(): String = author.ifEmpty { shareUser }

}