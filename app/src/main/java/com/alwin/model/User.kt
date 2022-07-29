package com.alwin.model

data class User(
    val admin: Boolean = false,
    val chapterTops: List<Any?> = emptyList(),
    val coinCount: Long = 0L,
    val collectIds: List<Long> = emptyList(),
    val email: String = "",
    val icon: String = "",
    val id: Long = 0L,
    val nickname: String = "",
    val password: String = "",
    val publicName: String = "",
    val token: String = "",
    val type: Int = 0,
    val username: String = ""
)