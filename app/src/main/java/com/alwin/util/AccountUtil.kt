package com.alwin.util

object AccountUtils {

    private var username = ""

    suspend fun isLogin(): Boolean {
        getUserName()
        return username.isNotEmpty()
    }

    suspend fun getUserName(): String {
        if (username.isEmpty()) {
            username = readUsername()
        }
        return username
    }
}