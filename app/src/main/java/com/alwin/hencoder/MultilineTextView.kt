package com.alwin.hencoder

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.alwin.android.R
import com.alwin.util.dp2px

internal val IMAGE_PADDING = 100.dp2px
internal val IMAGE_WIDTH = 100.dp2px

class MultilineTextView(context: Context?, attributeSet: AttributeSet?) :
    View(context, attributeSet) {

    private val text =
        "Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune kitabı oluşturmak üzere bir yazı galerisini alarak karıştırdığı 1500'lerden beri endüstri standardı sahte metinler olarak kullanılmıştır. Beşyüz yıl boyunca varlığını sürdürmekle kalmamış, aynı zamanda pek değişmeden elektronik dizgiye de sıçramıştır. 1960'larda Lorem Ipsum pasajları da içeren Letraset yapraklarının yayınlanması ile ve yakın zamanda Aldus PageMaker gibi Lorem Ipsum sürümleri içeren masaüstü yayıncılık yazılımları ile popüler olmuştur."

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp2px.toFloat()
    }
    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(
            getAvatar(IMAGE_WIDTH),
            width - IMAGE_WIDTH.toFloat(),
            IMAGE_PADDING.toFloat(),
            paint
        )

        val measuredWidth = floatArrayOf()
        paint.getFontMetrics(fontMetrics)
        var verticalOffset = -fontMetrics.top
        var start = 0
        while (start < text.length) {
            // 这行文字能使用的最大宽度
            val maxValue = if (verticalOffset + fontMetrics.bottom < IMAGE_PADDING
                || verticalOffset + fontMetrics.top > IMAGE_PADDING + IMAGE_WIDTH
            ) {
                width.toFloat()
            } else {
                width.toFloat() - IMAGE_WIDTH
            }
            val count = paint.breakText(
                text, start, text.length, true,
                maxValue, measuredWidth
            )
            canvas.drawText(text, start, start + count, 0f, verticalOffset, paint)
            start += count
            verticalOffset += paint.fontSpacing
        }
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    }
}