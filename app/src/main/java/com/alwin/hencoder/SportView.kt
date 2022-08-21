package com.alwin.hencoder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.alwin.util.dp2px

internal val CIRCLE_COLOR = Color.parseColor("#90A4AE")
internal val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")

class SportView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 20.dp2px.toFloat()
        textAlign = Paint.Align.LEFT
    }

    override fun onDraw(canvas: Canvas) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10.dp2px.toFloat()
        paint.color = CIRCLE_COLOR
        canvas.drawOval(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            paint
        )
        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            270f,
            210f,
            false,
            paint
        )
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 3.dp2px.toFloat()
        val str = "abba"
        val bounds = Rect()
        val fontMetrics = Paint.FontMetrics()
        paint.getFontMetrics(fontMetrics)
        // val offset = (fontMetrics.ascent + fontMetrics.descent) / 2
        paint.getTextBounds(str, 0, str.length, bounds)
        // val offset = (bounds.top + bounds.bottom) / 2
        canvas.drawText(
            str,
            0,
            str.length,
            -bounds.left.toFloat(),
            -fontMetrics.ascent.toFloat(),
            paint
        )
    }
}