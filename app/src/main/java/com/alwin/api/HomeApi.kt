package com.alwin.api

import com.alwin.model.ApiResponse
import com.alwin.model.Article
import com.alwin.model.BannerModel
import com.alwin.model.PagerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeArticles(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int = 20
    ): ApiResponse<PagerResponse<MutableList<Article>>>

    /**
     * 获取banner数据
     */
    @GET("banner/json")
    fun getBannerData(): Call<ApiResponse<List<BannerModel>>>

    /**
     * 每日一问列表数据
     */
    @GET("wenda/list/{page}/json")
    fun getAskData(@Path("page") page: Int): Call<ApiResponse<PagerResponse<MutableList<Article>>>>
}
