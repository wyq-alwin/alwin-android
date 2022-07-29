package com.alwin.login.viewmodel

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import com.alwin.module.NetWorkModule
import com.alwin.util.SystemUtil
import com.alwin.util.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    suspend fun toLogin(user: String, pwd: String) : Boolean = withContext(Dispatchers.IO) {
        val result = NetWorkModule.provideMineApi().toLogin(user, pwd)
       if (result.data == null || result.errorCode < 0){
           SystemUtil.showToast(result.errorMsg)
           return@withContext false
       }
        return@withContext true
    }

    suspend fun toRegister(user: String, pwd: String) = withContext(Dispatchers.IO) {
        val result = NetWorkModule.provideMineApi().toRegister(user, pwd, pwd)
        if (result.data == null || result.errorCode < 0){
            SystemUtil.showToast(result.errorMsg)
            return@withContext false
        }

        return@withContext true
    }

}
