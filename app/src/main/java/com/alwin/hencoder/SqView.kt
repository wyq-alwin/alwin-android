package com.alwin.hencoder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.alwin.util.dp2px

class SqView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    val PADDNG = 50.dp2px.toFloat()
    val RADIUS = 100.dp2px.toFloat()
    val paint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = (PADDNG + RADIUS) * 2

        setMeasuredDimension(
            resolveSize(size.toInt(), widthMeasureSpec),
            resolveSize(size.toInt(), heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(PADDNG + RADIUS, PADDNG + RADIUS, RADIUS, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        event.action
        event.actionMasked
        MotionEvent.ACTION_DOWN
        MotionEvent.ACTION_POINTER_DOWN

        performClick()
        return true
    }
    // private fun getAvatar(): Bitmap {
    //     val options = BitmapFactory.Options()
    //     options.inJustDecodeBounds = true
    //     BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    //     options.inJustDecodeBounds = false
    //     options.inDensity = options.outWidth
    //     options.inTargetDensity = 200.dp2px
    //     return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    // }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    val result = mutableListOf<String>()

    fun permutation(s: String): Array<String> {
        val chs = s.toCharArray()
        chs.sort()


        return result.toTypedArray()
    }

    fun help(s: String, index: Int, path: StringBuilder, visited: BooleanArray) {

    }
}