package com.alwin.hencoder.touch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import androidx.core.util.forEach
import com.alwin.hencoder.getAvatar
import com.alwin.util.dp2px

class TouchView1(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val bitmap = getAvatar(resources, 200.dp2px)
    private var downX = 0f
    private var downY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var oX = 0f
    private var oY = 0f
    private var trackId = 0

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 4.dp2px.toFloat()
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
    private val paths = SparseArray<Path>()

    override fun onDraw(canvas: Canvas) {
        paths.forEach { key, value ->
            canvas.drawPath(value, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val id = event.getPointerId(event.actionIndex)
                if (paths[id] == null) {
                    paths[id] = Path().apply {
                        moveTo(event.getX(event.actionIndex), event.getY(event.actionIndex))
                    }
                    invalidate()
                }
            }
            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                paths.remove(event.getPointerId(event.actionIndex))
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until paths.size()) {
                    paths.get(event.getPointerId(i)).lineTo(event.getX(i), event.getY(i))
                }
                invalidate()
            }
        }

        return true
    }
}