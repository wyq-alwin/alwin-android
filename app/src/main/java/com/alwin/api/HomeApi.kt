package com.alwin.api

import com.alwin.model.HomeArticleResponse
import com.alwin.model.AlwinResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {
    @GET("article/list/{page}/json")
    fun getHomeArticles(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int = 20
    ): Call<AlwinResponse<HomeArticleResponse>>
}