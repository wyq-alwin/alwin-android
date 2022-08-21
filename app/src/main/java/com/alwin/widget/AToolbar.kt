package com.alwin.widget

import android.content.Context
import android.content.res.ColorStateList
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.StyleRes
import androidx.appcompat.R
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.TextViewCompat
import com.alwin.util.sp2px

/**
 * Title居中的Toolbar
 */
class AToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    Toolbar(context, attrs) {
    private var titleTextAppearance = 0
    private var titleTextColor: ColorStateList? = null
    private var titleTextView: TextView? = null
    private var titleText: CharSequence? = null

    init {
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.Toolbar, 0, 0)
        titleTextAppearance = ta.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0)
        titleText = ta.getText(R.styleable.Toolbar_title)
        if (!titleText.isNullOrEmpty()) {
            title = titleText
        }
        if (ta.hasValue(R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(ta.getColorStateList(R.styleable.Toolbar_titleTextColor)!!)
        }
        ta.recycle()
    }

    override fun setTitle(title: CharSequence?) {
        if (!title.isNullOrEmpty()) {
            if (titleTextView == null) {
                titleTextView = AppCompatTextView(context).apply {
                    isSingleLine = true
                    ellipsize = TextUtils.TruncateAt.END
                    addView(
                        this,
                        LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.CENTER
                        )
                    )
                }
            }
            if (titleTextAppearance != 0) {
                titleTextView?.apply {
                    TextViewCompat.setTextAppearance(this, titleTextAppearance)
                }
            }
            if (titleTextColor != null) {
                titleTextView?.setTextColor(titleTextColor)
            }
        } else if (titleTextView != null) {
            removeView(titleTextView)
        }
        titleTextView?.text = title
        titleText = title
    }

    override fun getTitle(): CharSequence? {
        return titleText
    }

    override fun setTitleTextColor(@NonNull color: ColorStateList) {
        titleTextColor = color
        titleTextView?.setTextColor(color)
    }

    override fun setTitleTextAppearance(context: Context, @StyleRes resId: Int) {
        titleTextAppearance = resId
        titleTextView?.apply {
            TextViewCompat.setTextAppearance(this, resId)
        }
    }
}
