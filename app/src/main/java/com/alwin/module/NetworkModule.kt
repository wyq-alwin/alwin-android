package com.alwin.module

import com.alwin.api.CommonApi
import com.alwin.api.HomeApi
import com.alwin.api.MeApi
import com.alwin.api.OfficialApi
import com.alwin.constant.ApiConstant
import com.alwin.util.SystemUtil
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetWorkModule {

    private val cookieJar: PersistentCookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(SystemUtil.application))
    }

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .cookieJar(cookieJar)
        .build()

    private val retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstant.WAN_ANDROID_HOST)
            .client(okHttpClient)
            .build()

    fun provideHomeApi(): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    fun provideMeApi(): MeApi {
        return retrofit.create(MeApi::class.java)
    }

    fun provideOfficialApi(): OfficialApi {
        return retrofit.create(OfficialApi::class.java)
    }

    fun provideCommonApi(): CommonApi {
        return retrofit.create(CommonApi::class.java)
    }
}