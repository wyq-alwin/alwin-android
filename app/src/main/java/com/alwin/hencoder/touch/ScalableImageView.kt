package com.alwin.hencoder.touch

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.alwin.hencoder.getAvatar
import com.alwin.util.dp2px
import java.lang.Float.max
import java.lang.Float.min

class ScalableImageView(context: Context, attributeSet: AttributeSet?) :
    View(context, attributeSet) {
    private val AVATAR_WIDTH = 300.dp2px
    private val EXRTRA_SCALE_FRACTOR = 1.5f // 为了大图能滑动
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(resources, AVATAR_WIDTH)
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var isBig = false
    private val gestureDetector = GestureDetectorCompat(context, CustomOnGestureListener())
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
    private val scroller = OverScroller(context)

    // ScaleGestureDetectorCompat不是兼容版本，而是兼容辅助类
    private val scaleGestureListener = CustomOnScaleGestureListener()
    private val scaleGestureDetector = ScaleGestureDetector(context, scaleGestureListener)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        originalOffsetX = (width - AVATAR_WIDTH) / 2f
        originalOffsetY = (height - AVATAR_WIDTH) / 2f
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.width.toFloat() * EXRTRA_SCALE_FRACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXRTRA_SCALE_FRACTOR
        }
        currentScale = smallScale
        scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
    }

    override fun onDraw(canvas: Canvas) {
        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    private fun fixOffset() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }

    inner class CustomOnGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onScroll(
            downEvent: MotionEvent,
            currentEvent: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            // distanceX是downEvent.x - currentEvent.x
            // 相当于onMove返回值没用处
            if (isBig) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffset()
                invalidate()
            }
            return false
        }

        override fun onFling(
            downEvent: MotionEvent,
            currentEvent: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            // 快滑
            if (isBig) {
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    (-(bitmap.width * bigScale - width) / 2).toInt(),
                    ((bitmap.width * bigScale - width) / 2).toInt(),
                    (-(bitmap.height * bigScale - height) / 2).toInt(),
                    ((bitmap.height * bigScale - height) / 2).toInt()
                )
                postOnAnimation {
                    refresh.invoke()
                }
            }
            return false
        }

        private val refresh = { refresh() }

        private fun refresh() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                postOnAnimation { refresh.invoke() }
            }
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            // 不是双击才触发；和双击方法配合
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            // 双击，点击间隔在40ms-300ms;返回值无效
            if (!scaleAnimator.isRunning) {
                isBig = !isBig
                if (isBig) {
                    offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
                    offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
                    fixOffset()
                    scaleAnimator.start()
                } else {
                    scaleAnimator.reverse()
                }
            }
            return true
        }
    }

    inner class CustomOnScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            // 返回值表示是和初始状态的比值还是和上一状态的比值
            currentScale *= scaleGestureDetector.scaleFactor
            currentScale = currentScale.coerceAtLeast(smallScale).coerceAtMost(bigScale)
            return true
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            val tempCurrentScale = currentScale * detector.scaleFactor
            if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
                return false
            } else {
                currentScale *= detector.scaleFactor
                return true
            }
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }
    }
}