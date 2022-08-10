package com.alwin.hencoder.touch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_UP) {
            performClick()
        }
        return true
    }
}