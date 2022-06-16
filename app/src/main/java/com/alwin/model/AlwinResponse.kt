package com.alwin.model

import android.system.StructMsghdr

data class AlwinResponse<T>(
    val data: T,
    val errorCode: Long = 0,
    val errorMsg: String = ""
)