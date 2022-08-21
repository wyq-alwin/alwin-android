package com.alwin.me

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.alwin.module.NetWorkModule

class MeViewModel : ViewModel() {
    val isLogin = MutableLiveData<Boolean>()

    val articleFlow by lazy {
        Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 5),
            pagingSourceFactory = { MeFollowPagingSource(NetWorkModule.provideMeApi()) }
        ).flow.cachedIn(viewModelScope)
    }
}