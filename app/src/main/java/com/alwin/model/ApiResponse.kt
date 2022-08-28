package com.alwin.model

data class ApiResponse<T>(
    val data: T,
    val errorCode: Long = 0,
    val errorMsg: String = ""
)