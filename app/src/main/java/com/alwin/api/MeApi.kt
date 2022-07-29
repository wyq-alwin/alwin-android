package com.alwin.api

import com.alwin.model.ApiResponse
import com.alwin.model.PagerResponse
import com.alwin.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MeApi {

    /**
     * 登陆
     */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun toLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResponse<User?>

    /**
     * 注册
     */
    @POST("user/register")
    @FormUrlEncoded
    suspend fun toRegister(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    ): ApiResponse<User?>
}