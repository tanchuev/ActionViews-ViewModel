package com.tanchuev.actionviews.viewmodel.example.presentation.ui.widget

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.tanchuev.actionviews.viewmodel.example.presentation.R
import com.tanchuev.actionviews.viewmodel.utils.getAppCompatDrawable


/**
 * Created by marat.taychinov
 */
class ButtonVectorSupport : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val attributeArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonVectorSupport)

        val drawableLeft = context.getAppCompatDrawable(
            attributeArray,
            R.styleable.ButtonVectorSupport_drawableCompatLeft
        )
        val drawableRight = context.getAppCompatDrawable(
            attributeArray,
            R.styleable.ButtonVectorSupport_drawableCompatRight
        )
        val drawableBottom = context.getAppCompatDrawable(
            attributeArray,
            R.styleable.ButtonVectorSupport_drawableCompatBottom
        )
        val drawableTop = context.getAppCompatDrawable(
            attributeArray,
            R.styleable.ButtonVectorSupport_drawableCompatTop
        )

        setCompoundDrawablesWithIntrinsicBounds(
            drawableLeft,
            drawableTop,
            drawableRight,
            drawableBottom
        )
        attributeArray.recycle()
    }
}