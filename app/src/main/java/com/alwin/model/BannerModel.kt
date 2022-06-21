package com.alwin.model

data class BannerModel(
    val desc: String = "",
    val id: Int = 0,
    val imagePath: String = "",
    val isVisible: Int,
    val order: Int = 0,
    val title: String = "",
    val type: Int = 0,
    val url: String = ""
)