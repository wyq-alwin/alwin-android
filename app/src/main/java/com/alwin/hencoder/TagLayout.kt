package com.alwin.hencoder

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import com.alwin.util.dp2px

class TagLayout(context: Context, attributeSet: AttributeSet?) : ViewGroup(context, attributeSet) {
    private val childBounds = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var lineMaxHeight = 0
        var lineMaxWidth = 0
        var justBreakLine = false
        val interval = 5.dp2px
        for ((index, child) in children.withIndex()) {
            // 第一次尝试测量child
            measureChildWithMargins(
                child,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                heightUsed
            )
            justBreakLine = false
            // 放不下的话，再次测量child
            if (MeasureSpec.getSize(widthMeasureSpec) != MeasureSpec.UNSPECIFIED
                && child.measuredWidth + widthUsed + interval >= MeasureSpec.getSize(
                    widthMeasureSpec
                )
            ) {
                heightUsed += 5.dp2px
                widthUsed = 0
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    widthUsed,
                    heightMeasureSpec,
                    heightUsed
                )
                justBreakLine = true
            }

            if (index > 0 && !justBreakLine) {
                widthUsed += 5.dp2px
            }
            // 记录child的位置
            if (childBounds.size <= index) {
                childBounds.add(Rect())
            }
            val childBounds = childBounds[index]
            childBounds.set(
                widthUsed,
                heightUsed,
                widthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )

            widthUsed += child.measuredWidth
            lineMaxWidth = lineMaxWidth.coerceAtLeast(widthUsed)
            lineMaxHeight = lineMaxHeight.coerceAtLeast(child.measuredHeight)
        }
        // 记录viewgroup本身的高度和宽度
        setMeasuredDimension(lineMaxWidth, lineMaxHeight + heightUsed)
    }

    // 只是使用onMeasure存下的结果
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            child.layout(
                childBounds[index].left,
                childBounds[index].top,
                childBounds[index].right,
                childBounds[index].bottom
            )
        }
    }

    // 配合measureChildWithMargins
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    // 非滑动viewgroup需要重写为false
    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }
}