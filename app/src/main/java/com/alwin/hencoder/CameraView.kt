package com.alwin.hencoder

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withClip
import androidx.core.graphics.withSave
import com.alwin.android.R

class CameraView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(IMAGE_WIDTH)
    private val camera = Camera()

    init {
        camera.rotateX(30f)
        camera.setLocation(0f, 0f, -2 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        // 上半部分
        // canvas.save()
        // canvas.translate(width / 2f, height / 2f)
        // canvas.rotate(-30f)
        // canvas.clipRect(
        //     -AVATAR_WIDTH / 2f * 2,
        //     -AVATAR_WIDTH / 2f * 2,
        //     AVATAR_WIDTH / 2f * 2,
        //     0f
        // )
        // canvas.rotate(30f)
        // canvas.translate(-width / 2f, -height / 2f)
        val matrix = Matrix()
        matrix.setTranslate(width / 2f, height / 2f)
        matrix.setRotate(10f)
        canvas.drawBitmap(
            bitmap,
            matrix,
            paint
        )


    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    }
}