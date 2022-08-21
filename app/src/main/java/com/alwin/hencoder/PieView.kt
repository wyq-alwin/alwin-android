package com.alwin.hencoder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val data = floatArrayOf(30f, 60f, 100f)
    private var sum = 0f
    private val color = arrayOf(Color.BLUE, Color.GREEN, Color.RED)
    var clickIndex: Int = 1
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        data.forEachIndexed { index, it ->
            paint.color = color[index]
            if (index == clickIndex) {
                canvas.save()
                val distanceX = (cos(Math.toRadians(sum + it / 2.0)) * 50f).toFloat()
                val distanceY = (sin(Math.toRadians(sum + it / 2.0)) * 50f).toFloat()
                canvas.translate(distanceX, distanceY)
            }
            canvas.drawArc(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                sum,
                it,
                true,
                paint
            )
            if (index == clickIndex) {
                canvas.restore()
            }
            sum += it
        }
    }

   // todo Alwin 拖拽
}