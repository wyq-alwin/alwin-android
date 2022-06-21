package com.alwin.model

data class HomeArticleResponse(
    val curPage: Int = 1,
    val datas: MutableList<Article> = mutableListOf(),
    val offset: Int = 20,
    val over: Boolean = false,
    val pageCount: Int = 624,
    val size: Int = 20,
    val total: Int = 12468
)