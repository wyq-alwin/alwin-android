package com.alwin.hencoder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.alwin.util.dp2px

internal val RADIUS = 100.dp2px.toFloat()
internal const val OPEN_ANGLE = 120
internal val DASH_WIDTH = 2.dp2px.toFloat()
internal val DASH_HEIGHT = 5.dp2px.toFloat()
internal val POINTER_LENGTH = 80.dp2px.toFloat()

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    lateinit var pathMeasure: PathMeasure
    private val path = Path()
    private val dash = Path()
    private lateinit var pathDashPathEffect: PathDashPathEffect

    init {
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CW)
        paint.strokeWidth = 3.dp2px.toFloat()
        paint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90.0f + OPEN_ANGLE / 2.0f,
            360f - OPEN_ANGLE,
        )
        pathMeasure = PathMeasure(path, false)
        pathDashPathEffect = PathDashPathEffect(
            dash,
            (pathMeasure.length - DASH_WIDTH) / 20f,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
        paint.pathEffect = pathDashPathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        val degree = mark2Radian(5)
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (width / 2f + POINTER_LENGTH * Math.cos(Math.toRadians(degree))).toFloat(),
            (height / 2f + POINTER_LENGTH * Math.sin(Math.toRadians(degree))).toFloat(),
            paint
        )
    }

    private fun mark2Radian(mark: Int): Double {
        return (90.0f + OPEN_ANGLE / 2.0f + (360f - OPEN_ANGLE) / 20 * mark).toDouble()
    }
}