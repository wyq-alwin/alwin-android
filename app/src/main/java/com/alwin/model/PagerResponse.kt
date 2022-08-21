package com.alwin.model

data class PagerResponse<T>(
    val curPage: Int = 1,
    val datas: T,
    val offset: Int = 20,
    val over: Boolean = false,
    val pageCount: Int = 624,
    val size: Int = 20,
    val total: Int = 12468
) {
    /**
     * 数据是否为空
     */
    fun isEmpty() = (datas as? List<*>)?.size == 0

    /**
     * 是否为非首页
     */
    fun isRefresh() = offset == 0

    /**
     * 是否还有更多数据
     */
    fun hasMore() = !over
}