package com.alwin.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.alwin.login.viewmodel.username
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

suspend fun writeUsername(name: String) {
    SystemUtil.application.dataStore.edit { settings ->
        settings[username] = name
    }
}

suspend fun readUsername(): String = withContext(Dispatchers.IO) {
    var result = ""
    SystemUtil.application.dataStore.edit { settings ->
        result = settings[username] ?: ""
    }
    return@withContext result
}
