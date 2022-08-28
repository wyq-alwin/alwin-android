package com.alwin.api

import com.alwin.model.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface CommonApi {

    /**
     * 收藏文章
     * @param id1没有用处,只用来防止报错：Form-encoded method must contain at least one @Field
     */
    @FormUrlEncoded
    @POST("lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Long, @Field("id") id1: Long = 0): ApiResponse<Void>

    /**
     * 取消收藏文章
     */
    @FormUrlEncoded
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollectArticle(
        @Path("id") id: Long,
        @Field("id") id1: Long = 0
    ): ApiResponse<Void>
}
