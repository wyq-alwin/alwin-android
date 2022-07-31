package com.alwin.widget

import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alwin.model.BannerModel
import com.alwin.util.dp2px
import com.alwin.webview.WebActivity
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.youth.banner.adapter.BannerAdapter

/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
class ImageAdapter(datas: List<BannerModel> = emptyList()) :
    BannerAdapter<BannerModel, ImageAdapter.BannerViewHolder>(datas) {
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = SimpleDraweeView(parent.context).apply {
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: BannerModel,
        position: Int,
        size: Int
    ) {
        holder.bind(data)
    }

    class BannerViewHolder(private val imageView: SimpleDraweeView) :
        RecyclerView.ViewHolder(imageView) {
        fun bind(data: BannerModel) {
            val roundingParams = RoundingParams.fromCornersRadius(8.dp2px.toFloat())
            imageView.hierarchy.roundingParams = roundingParams
            imageView.setImageURI(data.imagePath)

            imageView.setOnClickListener {
                val intent = Intent().apply {
                    action = "android.intent.action.VIEW"
                    addCategory("android.intent.category.BROWSABLE")
                    setClass(itemView.context, WebActivity::class.java)
                    putExtra("web_uri", data.url)
                }
                it.context.startActivity(intent)
            }
        }
    }
}
