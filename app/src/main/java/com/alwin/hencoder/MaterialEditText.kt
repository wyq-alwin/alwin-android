package com.alwin.hencoder

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.alwin.android.R
import com.alwin.util.dp2px

class MaterialEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    private val TEXT_SIZE = 12.dp2px
    private val TEXT_MARGIN = 4.dp2px
    private val HORIZONTAL_OFFSET = 5.dp2px
    private var VERTICAL_OFFSET = 20.dp2px
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE.toFloat()
    }

    private var useFloatingLabel = true
        set(value) {
            if (value != field) {
                field = value
                if (value) {
                    setPadding(
                        paddingLeft,
                        paddingTop + TEXT_MARGIN + TEXT_SIZE,
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        paddingTop - TEXT_MARGIN - TEXT_SIZE,
                        paddingRight,
                        paddingBottom
                    )
                }
            }
        }
    private var floatShown = false
    var floatFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val animator = ObjectAnimator.ofFloat(this, "floatFraction", 1f, 0f).setDuration(200)

    init {
        setPadding(paddingLeft, paddingTop + TEXT_MARGIN + TEXT_SIZE, paddingRight, paddingBottom)
        // val arr = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        // useFloatingLabel = arr.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        // arr.recycle()
        val arr = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatingLabel))
        useFloatingLabel = arr.getBoolean(0, true)
        arr.recycle()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (floatShown && text.isNullOrEmpty()) {
            floatShown = false
            animator.start()
        } else if (!floatShown && !text.isNullOrEmpty()) {
            floatShown = true
            animator.reverse()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!useFloatingLabel) {
            return
        }
        paint.alpha = (floatFraction * 0xff).toInt()
        val ver = VERTICAL_OFFSET.toFloat() + 20.dp2px * (1 - floatFraction)
        canvas.drawText(
            hint.toString(),
            HORIZONTAL_OFFSET.toFloat(),
            ver.toFloat(),
            paint
        )
    }
}