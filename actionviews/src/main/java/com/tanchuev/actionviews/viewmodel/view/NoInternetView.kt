package com.tanchuev.actionviews.viewmodel.view

import android.view.View
import com.tanchuev.actionviews.viewmodel.utils.setVisibility

/**
 * @author tanchuev
 */
interface NoInternetView {
    fun setButtonClickListener(clickListener: View.OnClickListener)

    fun setVisibility(visible: Boolean, contentView: View) {
        if (visible) {
            //скрывают все остальные view(ActionViews + contentActionView)
            setVisibility(View.GONE, contentView)
            setVisibility(View.VISIBLE, this as View)
        } else {
            setVisibility(View.VISIBLE, contentView)
            setVisibility(View.GONE, this as View)
        }
    }
}
