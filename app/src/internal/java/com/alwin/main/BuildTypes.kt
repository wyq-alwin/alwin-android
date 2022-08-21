package com.alwin.main

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup

fun Activity.drawBadge() {
    val view = View(this)
    view.setBackgroundColor(Color.GREEN)
    val decorView = window.decorView as ViewGroup
    decorView.addView(view, 200, 200)
}