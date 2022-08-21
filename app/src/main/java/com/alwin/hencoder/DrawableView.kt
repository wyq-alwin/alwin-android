package com.alwin.hencoder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class DrawableView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {

    val drawable = MeshDrawable()

    override fun onDraw(canvas: Canvas) {
        drawable.bounds = Rect(0, 0, width, height)
        drawable.draw(canvas)
    }
}