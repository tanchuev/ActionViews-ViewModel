package com.tanchuev.actionviews.viewmodel.widget

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.tanchuev.actionviews.viewmodel.R
import com.tanchuev.actionviews.viewmodel.view.TopLoadingView

class ProgressBar : android.widget.ProgressBar, TopLoadingView {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ProgressBar, 0, 0)

        try {
            indeterminateDrawable.setColorFilter(
                    a.getColor(
                            R.styleable.ProgressBar_progressColor,
                            ContextCompat.getColor(
                                    context,
                                    R.color.lightTransparent50
                            )
                    ),
                    PorterDuff.Mode.MULTIPLY
            )
        } finally {
            a.recycle()
        }
    }
}