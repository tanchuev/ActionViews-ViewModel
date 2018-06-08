package com.tanchuev.actionviews.viewmodel.widget

import android.content.Context
import android.util.AttributeSet
import com.tanchuev.actionviews.viewmodel.view.EmptyContentView

/**
 * @author tanchuev
 */

class EmptyContentView(context: Context, attrs: AttributeSet) : SimplePlaceholderView(context, attrs),
    EmptyContentView {
    override var isContentEmpty: Boolean = true
}
