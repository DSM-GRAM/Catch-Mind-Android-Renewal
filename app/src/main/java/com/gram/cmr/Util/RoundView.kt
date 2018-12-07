package com.gram.cmr.Util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.R.attr.strokeWidth
import android.graphics.*
import androidx.annotation.ColorInt


class RoundView: View {
    private var backgroundColor: Int? = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {
        val pnt = Paint()
        pnt.style = Paint.Style.STROKE
        pnt.strokeWidth = strokeWidth.toFloat()
        pnt.color = backgroundColor!!
        pnt.isDither = true
        pnt.strokeJoin = Paint.Join.ROUND
        pnt.strokeCap = Paint.Cap.ROUND
        pnt.pathEffect = CornerPathEffect(strokeWidth.toFloat())
        pnt.isAntiAlias = true

        val path = Path()
        val oval = RectF()
        oval.set(strokeWidth.toFloat(), strokeWidth.toFloat(), (width - strokeWidth).toFloat(), (height - strokeWidth).toFloat())
        path.addRoundRect(oval, canvas.height.toFloat(), canvas.height.toFloat(), Path.Direction.CCW)

        canvas.drawPath(path, pnt)
        super.onDraw(canvas)
    }

    override fun setBackgroundColor(@ColorInt color: Int) {
        backgroundColor = color
    }
}

