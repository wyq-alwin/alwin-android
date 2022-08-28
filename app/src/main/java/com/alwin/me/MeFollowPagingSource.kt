package com.alwin.me

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alwin.api.MeApi
import com.alwin.model.Article

class MeFollowPagingSource(private val meApi: MeApi) :
    PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val pageNum = params.key ?: 0
            val response = meApi.getFollowArticles(pageNum).data
            val preKey = if (pageNum > 1) pageNum.minus(1) else null
            val nextKey = if (response.hasMore()) {
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