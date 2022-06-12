package com.alwin.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alwin.android.R

/**
 * 首页的底部栏Item：icon+text
 */
class BottomItemView : RelativeLayout {

    private val iconView: ImageView by lazy { findViewById(R.id.icon) }

    private val textView: TextView by lazy { findViewById(R.id.text) }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
        : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        inflate(context, R.layout.view_bottom_item, this)
        context.obtainStyledAttributes(attrs, R.styleable.BottomItemView).let {
            setIcon(it.getResourceId(R.styleable.BottomItemView_icon, 0))
            setText(it.getResourceId(R.styleable.BottomItemView_text, 0))
            it.recycle()
        }
    }

    private fun setIcon(@DrawableRes iconRes: Int) {
        if (iconRes == 0) {
            return
        }
        iconView.setImageResource(iconRes)
        invalidate()
    }

   private fun setText(@StringRes textRes: Int) {
        if (textRes == 0) {
            return
        }
        textView.text = resources.getText(textRes)
        invalidate()
    }
}