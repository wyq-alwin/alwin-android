package com.alwin.home.viewholder

import android.content.Intent
import android.widget.TextView
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.coroutineScope
import com.alwin.android.R
import com.alwin.android.databinding.ItemHomeArticleBinding
import com.alwin.model.Article
import com.alwin.module.NetWorkModule
import com.alwin.util.SystemUtil
import com.alwin.webview.WebActivity
import com.alwin.widget.LifecycleViewHolder
import kotlinx.coroutines.launch

class ArticleViewHolder(private val binding: ItemHomeArticleBinding) :
    LifecycleViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val intent = Intent().apply {
                action = "android.intent.action.VIEW"
                addCategory("android.intent.category.BROWSABLE")
                setClass(itemView.context, WebActivity::class.java)
                putExtra("web_uri", model?.link ?: "")
            }
            it.context.startActivity(intent)
        }
        binding.follow.setOnClickListener {
            model ?: return@setOnClickListener
            model!!.collect = !model!!.collect
            if (model!!.collect) {
                (it as TextView).apply {
                    text = context.getString(R.string.collected)
                }
                collectArticle(model?.id ?: 0)
            } else {
                (it as TextView).apply {
                    text = context.getString(R.string.to_collect)
                }
                unCollectArticle(model?.id ?: 0)
            }
        }
    }

    var model: Article? = null

    private val lifecycleRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }

    fun bind(article: Article?) {
        article ?: return
        model = article
        binding.apply {
            title.text = article.title
            publishTime.text = SystemUtil.long2String(article.publishTime)
            follow.text =
                binding.root.context.getString(if (article.collect) R.string.collected else R.string.to_collect)
            if (article.author.isEmpty()) {
                author.text = article.shareUser
                chapter.text = "${article.superChapterName} | ${article.chapterName}"
            } else {
                author.text = article.author
                chapter.text =
                    article.shareUser.ifEmpty { "${article.superChapterName} | ${article.chapterName}" }
            }
        }
    }

    private fun collectArticle(id: Long) {
        lifecycleRegistry.coroutineScope.launch {
            NetWorkModule.provideCommonApi().collectArticle(id)
        }
    }

    private fun unCollectArticle(id: Long) {
        lifecycleRegistry.coroutineScope.launch {
            NetWorkModule.provideCommonApi().unCollectArticle(id)
        }
    }
}