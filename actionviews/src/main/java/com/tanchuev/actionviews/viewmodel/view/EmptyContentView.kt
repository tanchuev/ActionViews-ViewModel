package com.tanchuev.actionviews.viewmodel.view

import android.view.View
import com.tanchuev.actionviews.viewmodel.utils.setVisibility

/**
 * @author tanchuev
 */
interface EmptyContentView {
    var isContentEmpty: Boolean

    fun setButtonClickListener(onClickListener: View.OnClickListener)

    fun setVisibility(visible: Boolean, contentView: View) {
        val view = this as View
        if (visible && isContentEmpty) {
            setVisibility(View.GONE, contentView)
            setVisibility(View.VISIBLE, view)
        } else {
            setVisibility(View.GONE, view)
            setVisibility(View.VISIBLE, contentView)
        }
    }
}