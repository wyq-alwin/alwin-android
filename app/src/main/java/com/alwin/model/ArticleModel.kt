package com.alwin.model

data class ArticleModel(
    val apkLink: String = "",
    val audit: Long,
    val author: String = "",
    val canEdit: Boolean,
    val chapterId: Long,
    val chapterName: String = "",
    val collect: Boolean,
    val courseId: Long,
    val desc: String = "",
    val descMd: String = "",
    val envelopePic: String = "",
    val fresh: Boolean,
    val host: String = "",
    val id: Long,
    val link: String = "",
    val niceDate: String = "",
    val niceShareDate: String = "",
    val origin: String = "",
    val prefix: String = "",
    val projectLink: String = "",
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String = "",
    val superChapterId: Long,
    val superChapterName: String = "",
    val tags: List<TagModel> = emptyList(),
    val title: String = "",
    val type: Int,
    val userId: Long,
    val visible: Int,
    val zan: Long
)

data class TagModel(val name: String = "", val url: String = "")

