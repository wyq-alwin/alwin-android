package com.alwin.home.viewholder

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.alwin.android.databinding.ItemHomeArticleBinding
import com.alwin.model.Article
import com.alwin.util.SystemUtil
import com.alwin.webview.WebActivity

class ArticleViewHolder(private val binding: ItemHomeArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article?) {
        article ?: return
        binding.apply {
            title.text = article.title
            publishTime.text = SystemUtil.long2String(article.publishTime)
            if (article.author.isEmpty()) {
                author.text = article.shareUser
                chapter.text = "${article.superChapterName} | ${article.chapterName}"
            } else {
                author.text = article.author
                chapter.text =
                    article.shareUser.ifEmpty { "${article.superChapterName} | ${article.chapterName}" }
            }
            root.setOnClickListener {
                val intent = Intent().apply {
                    action = "android.intent.action.VIEW"
                    addCategory("android.intent.category.BROWSABLE")
                    setClass(itemView.context, WebActivity::class.java)
                    putExtra("web_uri", article.link)
                }
                it.context.startActivity(intent)
            }
        }
    }
}