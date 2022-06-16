package com.alwin.module

import com.alwin.api.HomeApi
import com.alwin.api.MineApi
import com.alwin.api.OfficialApi
import com.alwin.constant.ApiConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstant.WAN_ANDROID_HOST)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMineApi(retrofit: Retrofit): MineApi {
        return retrofit.create(MineApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOfficialApi(retrofit: Retrofit): OfficialApi {
        return retrofit.create(OfficialApi::class.java)
    }
}