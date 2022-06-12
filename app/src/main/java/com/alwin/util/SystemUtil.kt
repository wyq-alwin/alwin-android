package com.alwin.util

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import android.view.Window
import android.view.WindowManager

object SystemUtil {

    /**
     * 设置沉浸式状态栏
     */
    fun setStatusBar(window: Window){
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.or(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(window: Window, context: Context): Int {
        val localRect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(localRect)
        var statusBarHeight = localRect.top
        if (statusBarHeight == 0) {
            try {
                val localClass = Class.forName("com.android.internal.R\$dimen")
                val localObject = localClass.newInstance()
                val i5 =
                    localClass.getField("status_bar_height")[localObject].toString().toInt()
                statusBarHeight = context.resources.getDimensionPixelSize(i5)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (statusBarHeight == 0) {
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
            }
        }
        return statusBarHeight
    }
}