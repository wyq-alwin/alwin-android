package com.alwin.hencoder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.alwin.util.dp2px
import java.util.*

private val STRS = arrayOf(
    "北京",
    "上海",
    "天津",
    "黑龙江",
    "北京1",
    "上海1",
    "天津1",
    "黑龙江1",
    "北京2",
    "上海2",
    "天津2",
    "黑龙江2",
    "黑龙江1111",
    "北京2111",
    "上海2111",
    "天津2111",
    "黑龙江211"
)
private val COLORS = intArrayOf(
    Color.parseColor("#E91E63"),
    Color.parseColor("#673AB7"),
    Color.parseColor("#3F51B5"),
    Color.parseColor("#2196F3"),
    Color.parseColor("#009688"),
    Color.parseColor("#FF9800"),
    Color.parseColor("#FF5722"),
    Color.parseColor("#795548")
)
private val TEXT_SIZES = intArrayOf(16, 22, 28)
private val CORNER_RADIUS = 4.dp2px.toFloat()
private val X_PADDING = 16.dp2px
private val Y_PADDING = 8.dp2px

class ColoredTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val random = Random()

    init {
        text = STRS[random.nextInt(STRS.size)]
        setTextColor(Color.WHITE)
        // textSize = TEXT_SIZES[random.nextInt(3)].toFloat()
        paint.color = COLORS[random.nextInt(COLORS.size)]
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            CORNER_RADIUS,
            CORNER_RADIUS,
            paint
        )
        super.onDraw(canvas)
    }
}