package com.alwin.hencoder

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import com.alwin.util.dp2px

class TagLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val childrenBounds = mutableListOf<Rect>()
    private var paddingVer = 8.dp2px
    private var paddingHor = 8.dp2px
    private var widthUsed = 0
    private var heightUsed = 0
    private var curWidthMax = 0
    private var curHeightMax = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        widthUsed = paddingHor
        heightUsed = paddingVer
        curWidthMax = 0
        curHeightMax = 0
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec) - marginLeft - marginRight
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)

        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(
                child,
                widthMeasureSpec,
                2 * paddingHor,
                heightMeasureSpec,
                heightUsed
            )
            if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED && paddingHor + widthUsed + child.measuredWidth > parentWidth) {
                heightUsed += curHeightMax + paddingVer
                curHeightMax = 0
                curWidthMax = curWidthMax.coerceAtLeast(widthUsed)
                widthUsed = paddingHor
                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    widthUsed,
                    heightMeasureSpec,
                    heightUsed
                )
            } else {
                widthUsed += paddingHor
            }

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            childrenBounds[index].set(
                widthUsed,
                heightUsed,
                widthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )
            widthUsed += child.measuredWidth
            curHeightMax = curHeightMax.coerceAtLeast(child.measuredHeight)

            if (index == childrenBounds.lastIndex) {
                curWidthMax = curWidthMax.coerceAtLeast(widthUsed + child.measuredWidth)
                curHeightMax = curHeightMax.coerceAtLeast(child.measuredHeight)
            }
        }


        setMeasuredDimension(curWidthMax, heightUsed + curHeightMax)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        children.forEachIndexed { index, it ->
            it.layout(
                childrenBounds[index].left,
                childrenBounds[index].top,
                childrenBounds[index].right,
                childrenBounds[index].bottom
            )
        }
    }

    // 配合measureChildWithMargins
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    // 非滑动viewgroup需要重写为false，不论是不是直接继承的ViewGroup
    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }
}