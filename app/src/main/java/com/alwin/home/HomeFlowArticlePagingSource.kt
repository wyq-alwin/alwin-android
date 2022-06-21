package com.alwin.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alwin.api.HomeApi
import com.alwin.model.Article

class HomeFlowArticlePagingSource(private val homeApi: HomeApi) :
    PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val pageNum = params.key ?: 0
            val data = homeApi.getHomeArticles(pageNum).data.datas
            val preKey = if (pageNum > 1) pageNum.minus(1) else null
            // TODO 为什么一直请求网络
            val nextKey = if (pageNum > 5) {
                null
            } else {
                pageNum.plus(1)
            }
            return LoadResult.Page(
                data = data,
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
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}