package com.alwin.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.alwin.android.databinding.ItemHomeArticleBinding
import com.alwin.home.viewholder.ArticleViewHolder
import com.alwin.model.Article

class HomeArticleAdapter(diffCallback: DiffUtil.ItemCallback<Article> = ArticleDiff()) :
    PagingDataAdapter<Article, ArticleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val binding =
            ItemHomeArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        (holder as? ArticleViewHolder)?.bind(getItem(position))
    }

    class ArticleDiff : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}