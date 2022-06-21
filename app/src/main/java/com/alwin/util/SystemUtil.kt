package com.alwin.util

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import java.text.SimpleDateFormat
import java.util.*

object SystemUtil {

    /**
     * 设置沉浸式状态栏
     */
    fun setStatusBar(window: Window) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(activity: FragmentActivity): Int {
        val localRect = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(localRect)
        var statusBarHeight = localRect.top
        if (statusBarHeight == 0) {
            try {
                val localClass = Class.forName("com.android.internal.R\$dimen")
                val localObject = localClass.newInstance()
                val i5 =
                    localClass.getField("status_bar_height")[localObject].toString().toInt()
                statusBarHeight = activity.resources.getDimensionPixelSize(i5)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (statusBarHeight == 0) {
            val resourceId =
                activity.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                statusBarHeight = activity.resources.getDimensionPixelSize(resourceId)
            }
        }
        return statusBarHeight
    }

    /** Long时间戳转日期字符串 */
    fun long2String(timeStamp: Long): String {
        val date =  Date(timeStamp);
        val sdf =  SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }
}

val Int.dp2px
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.sp2px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )