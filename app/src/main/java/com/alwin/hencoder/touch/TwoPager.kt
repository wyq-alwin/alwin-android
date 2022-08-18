package com.alwin.hencoder.touch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.view.children
import kotlin.math.abs

class TwoPager(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private var downX = 0f
    private var downY = 0f
    private var downScrollX = 0f
    private var scrolling = false
    private val overScroller = OverScroller(context)
    private val viewConfiguration = ViewConfiguration.get(context)
    private val velocityTracker = VelocityTracker.obtain()
    private var minVelocity = viewConfiguration.scaledMinimumFlingVelocity
    private var maxVelocity = viewConfiguration.scaledMaximumFlingVelocity
    private var pagingSlop = viewConfiguration.scaledPagingTouchSlop

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 让所有子View使用自己的MeasureSpec测量大小
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        // 父布局的大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((i, child) in children.withIndex()) {
            if (i == 0) {
                // layout方法的参数是相对于父布局
                child.layout(0, 0, width, height)
            } else {
                child.layout(width, 0, 2 * width, height)
            }
        }
    }

    // 手指触摸到子View，会调用这里，MotionEvent.ACTION_DOWN得保持一致
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var result = false
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                scrolling = false
                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE -> {
                if (scrolling && abs(ev.x - downX) >= pagingSlop) {
                    scrolling = true
                    // 让父布局不处理
                    parent.requestDisallowInterceptTouchEvent(true)
                    // 让子布局不处理
                    result = true
                }
            }
        }
        return result
    }

    // 手指没有触摸到子View，直接放在空白处，会调用这里，MotionEvent.ACTION_DOWN得保持一致
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_MOVE) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                val xVelocity = velocityTracker.xVelocity
                val targetPage =
                    if ((xVelocity < minVelocity && scrollX > width / 2) || (xVelocity >= minVelocity && xVelocity < 0)) {
                        1
                    } else {
                        0
                    }
                val scrollDistance =
                    if (targetPage == 1) {
                        width - scrollX
                    } else {
                        -scrollX
                    }
                overScroller.startScroll(scrollX, 0, scrollDistance, 0)
                postInvalidateOnAnimation()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = (downX - ev.x + downScrollX).toInt().coerceAtLeast(0).coerceAtMost(width)
                scrollTo(dx, 0)
            }
        }
        return true
    }

    // onDraw会调用这个方法
    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }
}