package com.alwin.login

import androidx.lifecycle.ViewModel
import com.alwin.module.NetWorkModule
import com.alwin.util.SystemUtil
import com.alwin.util.writeUsername
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    suspend fun toLogin(user: String, pwd: String): Boolean = withContext(Dispatchers.IO) {
        val result = NetWorkModule.provideMeApi().toLogin(user, pwd)
        if (result.data == null || result.errorCode < 0) {
            SystemUtil.showToast(result.errorMsg)
            return@withContext false
        }
        writeUsername(result.data.username)
        return@withContext true
    }

    suspend fun toRegister(user: String, pwd: String) = withContext(Dispatchers.IO) {
        val result = NetWorkModule.provideMeApi().toRegister(user, pwd, pwd)
        if (result.data == null || result.errorCode < 0) {
            SystemUtil.showToast(result.errorMsg)
            return@withContext false
        }
        writeUsername(result.data.username)
        return@withContext true
    }
}
