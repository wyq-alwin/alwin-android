package com.alwin.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alwin.api.HomeApi
import com.alwin.model.Article

class HomeArticlePagingSource(private val homeApi: HomeApi) :
    PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val pageNum = params.key ?: 0
            val response = homeApi.getHomeArticles(pageNum).data
            val preKey = if (pageNum > 1) pageNum.minus(1) else null
            // todo 一直在请求网络？？？
            val nextKey = if (response.hasMore() && pageNum < 5) {
                pageNum.plus(1)
            } else {
               null
            }
            return LoadResult.Page(
                data = response.datas,
                prevKey = preKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
        }
        return LoadResult.Page(data = emptyList(), prevKey = null, nextKey = null)
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }
}