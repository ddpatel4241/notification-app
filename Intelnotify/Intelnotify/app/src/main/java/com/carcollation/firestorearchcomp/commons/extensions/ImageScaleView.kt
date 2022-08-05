package com.carcollation.firestorearchcomp.commons.extensions

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.os.Build
import android.annotation.TargetApi












class ImageScaleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr)  {





    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun ImageScaleView(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        init()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        recomputeImgMatrix()
    }

    override fun setFrame(l: Int, t: Int, r: Int, b: Int): Boolean {
        recomputeImgMatrix()
        return super.setFrame(l, t, r, b)
    }

    private fun init() {
        scaleType = ImageView.ScaleType.MATRIX
    }

    private fun recomputeImgMatrix() {

        val drawable = drawable ?: return

        val matrix = imageMatrix

        val scale: Float
        val viewWidth = width - paddingLeft - paddingRight
        val viewHeight = height - paddingTop - paddingBottom
        val drawableWidth = drawable.intrinsicWidth
        val drawableHeight = drawable.intrinsicHeight

        if (drawableWidth * viewHeight > drawableHeight * viewWidth) {
            scale = viewHeight.toFloat() / drawableHeight.toFloat()
        } else {
            scale = viewWidth.toFloat() / drawableWidth.toFloat()
        }

        matrix.setScale(scale, scale)
        imageMatrix = matrix
    }

}