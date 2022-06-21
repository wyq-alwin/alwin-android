package com.alwin.model

data class Article(
    val apkLink: String = "",
    val audit: Long = 0L,
    val author: String = "",
    val canEdit: Boolean = false,
    val chapterId: Long = 0L,
    val chapterName: String = "",
    val collect: Boolean = false,
    val courseId: Long = 0L,
    val desc: String = "",
    val descMd: String = "",
    val envelopePic: String = "",
    val fresh: Boolean = false,
    val host: String = "",
    val id: Long = 0L,
    val link: String = "",
    val niceDate: String = "",
    val niceShareDate: String = "",
    val origin: String = "",
    val prefix: String = "",
    val projectLink: String = "",
    val publishTime: Long = 0L,
    val realSuperChapterId: Int = 0,
    val selfVisible: Int = 0,
    val shareDate: Long = 0L,
    val shareUser: String = "",
    val superChapterId: Long = 0L,
    val superChapterName: String = "",
    val tags: List<TagModel> = emptyList(),
    val title: String = "",
    val type: Int = 0,
    val userId: Long = 0L,
    val visible: Int = 0,
    val zan: Long = 0L
)

data class TagModel(val name: String = "", val url: String = "")

