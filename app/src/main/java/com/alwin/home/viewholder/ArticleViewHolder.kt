package com.alwin.home.viewholder

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.alwin.android.databinding.ItemHomeArticleBinding
import com.alwin.model.Article
import com.alwin.util.SystemUtil
import github.leavesczy.robustwebview.WebViewDemoActivity

class ArticleViewHolder(private val binding: ItemHomeArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Article?) {
        data ?: return
        binding.apply {
            title.text = data.title
            publishTime.text = SystemUtil.long2String(data.publishTime)
            // collect.text = data.collect.
            if (data.author.isEmpty()) {
                author.text = data.shareUser
                chapter.text = "${data.superChapterName} | ${data.chapterName}"
            } else {
                author.text = data.author
                chapter.text =
                    data.shareUser.ifEmpty { "${data.superChapterName} | ${data.chapterName}" }
            }
            root.setOnClickListener {
                val intent = Intent(it.context, WebViewDemoActivity::class.java)
                it.context.startActivity(intent)
            }
        }
    }
}