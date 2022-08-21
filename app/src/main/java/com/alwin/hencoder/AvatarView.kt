package com.alwin.hencoder

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.alwin.android.R
import com.alwin.util.dp2px

internal val AVATAR_WIDTH = 120.dp2px

class AvatarView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bounds: RectF? = null

    override fun onDraw(canvas: Canvas) {
        bounds = RectF(
            width / 2f - AVATAR_WIDTH / 2f,
            height / 2f - AVATAR_WIDTH / 2f,
            width / 2f + AVATAR_WIDTH / 2f,
            height / 2f + AVATAR_WIDTH / 2f,
        )
        val count = canvas.saveLayer(bounds, null)
        canvas.drawOval(bounds!!, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(
            getAvatar(resources,AVATAR_WIDTH),
            width / 2f - AVATAR_WIDTH / 2f,
            height / 2f - AVATAR_WIDTH / 2f,
            paint
        )
        paint.xfermode = null
        canvas.restoreToCount(count)
    }
}

fun getAvatar(resources: Resources, width: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
}