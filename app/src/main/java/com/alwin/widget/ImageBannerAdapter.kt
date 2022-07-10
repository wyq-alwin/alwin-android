package com.alwin.widget

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alwin.model.BannerModel
import com.alwin.webview.WebActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.adapter.BannerAdapter

/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
class ImageAdapter(datas: List<BannerModel> = emptyList()) :
    BannerAdapter<BannerModel, ImageAdapter.BannerViewHolder>(datas) {
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
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

    class BannerViewHolder(private val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        fun bind(data: BannerModel){
            imageView.setImageURI(Uri.parse(data.imagePath))
            //图片加载自己实现
            Glide.with(itemView)
                .load(data.imagePath)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                .into(imageView)
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
