package com.alwin.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.alwin.api.HomeApi
import com.alwin.home.HomeArticlePagingSource
import com.alwin.model.ApiResponse
import com.alwin.model.Article
import com.alwin.model.BannerModel
import com.alwin.model.PagerResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class HomeFlowViewModel @Inject constructor(private val homeApi: HomeApi) : ViewModel() {
    val bannerModel = MutableLiveData<List<BannerModel>>()
    val askFlowModel = MutableLiveData<List<Article>>()

    val articleFlow by lazy {
        Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 5),
            pagingSourceFactory = { HomeArticlePagingSource(homeApi) }
        ).flow.cachedIn(viewModelScope)
    }

    suspend fun getBannerData() = withContext(Dispatchers.IO) {
        homeApi.getBannerData().enqueue(object : Callback<ApiResponse<List<BannerModel>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<BannerModel>>>,
                response: Response<ApiResponse<List<BannerModel>>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        bannerModel.value = it
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<BannerModel>>>, t: Throwable) {
            }
        })
    }

    fun getAskArticleData() {
        homeApi.getAskData(1)
            .enqueue(object : Callback<ApiResponse<PagerResponse<MutableList<Article>>>> {
                override fun onResponse(
                    call: Call<ApiResponse<PagerResponse<MutableList<Article>>>>,
                    response: Response<ApiResponse<PagerResponse<MutableList<Article>>>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.datas?.let {
                            askFlowModel.value = it
                        }
                    }
                }

                override fun onFailure(
                    call: Call<ApiResponse<PagerResponse<MutableList<Article>>>>,
                    t: Throwable
                ) {
                }
            })
    }
}