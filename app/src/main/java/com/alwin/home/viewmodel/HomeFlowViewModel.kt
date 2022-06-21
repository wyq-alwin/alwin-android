package com.alwin.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.alwin.api.HomeApi
import com.alwin.home.HomeFlowArticlePagingSource
import com.alwin.model.AlwinResponse
import com.alwin.model.BannerModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@ActivityRetainedScoped
class HomeFlowViewModel @Inject constructor(private val homeApi: HomeApi): ViewModel() {
    val bannerModel = MutableLiveData<List<BannerModel>>()

    val articleFlow by lazy {
        Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 5),
            pagingSourceFactory = { HomeFlowArticlePagingSource(homeApi) }
        ).flow.cachedIn(viewModelScope)
    }

    suspend fun getBannerData() = withContext(Dispatchers.IO){
        homeApi.getBannerData().enqueue(object : Callback<AlwinResponse<List<BannerModel>>>{
            override fun onResponse(
                call: Call<AlwinResponse<List<BannerModel>>>,
                response: Response<AlwinResponse<List<BannerModel>>>
            ) {
                if (response.isSuccessful){
                    response.body()?.data?.let {
                        bannerModel.value = it
                    }
                }
            }

            override fun onFailure(call: Call<AlwinResponse<List<BannerModel>>>, t: Throwable) {

            }
        })
    }
}