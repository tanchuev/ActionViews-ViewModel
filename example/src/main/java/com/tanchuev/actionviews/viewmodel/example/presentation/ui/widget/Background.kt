package com.tanchuev.actionviews.viewmodel.example.presentation.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView
import com.tanchuev.actionviews.viewmodel.example.presentation.R
import com.tanchuev.actionviews.viewmodel.utils.removeOnGlobalLayoutListener
import com.tanchuev.actionviews.viewmodel.utils.setImage
import kotlinx.android.synthetic.main.w_background.view.*

/**
 * @author tanchuev
 */

class Background(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {

    init {
        init(context, attrs)
    }

    private var backgroundWidth: Int = 0
    private var backgroundHeight: Int = 0

    private fun init(context: Context, attrs: AttributeSet) {
        View.inflate(context, R.layout.w_background, this)

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.Background, 0, 0)

        try {
            val background = a.getResourceId(R.styleable.Background_background, -1)
            isEnabled = false
            viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val width: Int
                    val height: Int
                    if (backgroundWidth == 0 && backgroundHeight == 0) {
                        width = this@Background.width
                        backgroundWidth = width
                        height = this@Background.height
                        backgroundHeight = height
                    } else {
                        width = backgroundWidth
                        height = backgroundHeight
                    }

                    ivBackground.setImage(background)

                    removeOnGlobalLayoutListener(this)
                }
            })
        } finally {
            a.recycle()
        }
    }
}
