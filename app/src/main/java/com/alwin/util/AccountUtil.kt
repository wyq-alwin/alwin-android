package com.alwin.util

object AccountUtils {

    private var username = ""

    fun isLogin(): Boolean {
        return username.isNotEmpty()
    }

    fun getUserName(): String {
        return username
    }

    fun setUserName(){

    }

}