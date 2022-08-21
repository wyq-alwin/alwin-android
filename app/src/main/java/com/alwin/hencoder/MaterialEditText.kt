package com.alwin.hencoder

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.alwin.android.R
import com.alwin.util.dp2px

private val TEXT_SIZE = 12.dp2px
private val TEXT_MARGIN = 8.dp2px
private val HORIZONTAL_OFFSET = 5.dp2px
private val VERTICAL_OFFSET = 24.dp2px
private val EXTRA_VERTICAL_OFFSET = 16.dp2px

class MaterialEditText(context: Context, attributeSet: AttributeSet) :
    AppCompatEditText(context, attributeSet) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val animator by lazy(LazyThreadSafetyMode.NONE) {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 1f, 0f)
    }
    private var floatingLabelShown = false
    private var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        paddingTop + TEXT_SIZE + TEXT_MARGIN,
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        paddingTop - TEXT_SIZE - TEXT_MARGIN,
                        paddingRight,
                        paddingBottom
                    )
                }
            }
        }

    init {
        paint.textSize = TEXT_SIZE.toFloat()
        for (i in 0 until attributeSet.attributeCount) {
            Log.d(
                "MaterialEditText",
                "${attributeSet.getAttributeName(i)}: ${attributeSet.getAttributeValue(i)}"
            )
        }
        // val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialEditText)
        // useFloatingLabel =
        //     typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        // typedArray.recycle()
        val typedArray =
            context.obtainStyledAttributes(attributeSet, intArrayOf(R.attr.useFloatingLabel))
        useFloatingLabel =
            typedArray.getBoolean(0, true)
        typedArray.recycle()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (!useFloatingLabel) {
            return
        }
        if (floatingLabelShown && text.isNullOrEmpty()) {
            floatingLabelShown = false
            animator.start()
        } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
            floatingLabelShown = true
            animator.reverse()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!useFloatingLabel) {
            return
        }
        paint.alpha = (floatingLabelFraction * 0xff).toInt()
        canvas.drawText(
            hint.toString(),
            HORIZONTAL_OFFSET.toFloat(),
            VERTICAL_OFFSET.toFloat() + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction),
            paint
        )
    }
}