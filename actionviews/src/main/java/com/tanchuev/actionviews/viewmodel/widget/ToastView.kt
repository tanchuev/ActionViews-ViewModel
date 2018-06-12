package com.tanchuev.actionviews.viewmodel.widget

import android.content.Context
import android.support.annotation.StringRes
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.l_toast_view.view.*
import com.tanchuev.actionviews.viewmodel.R
import com.tanchuev.actionviews.viewmodel.view.ErrorView

/**
 * @author tanchuev
 */
class ToastView(context: Context) : LinearLayout(context), ErrorView {

    init {
        init()
    }

    private fun init() {
        inflate(context, R.layout.l_toast_view, this)
    }

    override fun showError(@StringRes errorMessageId: Int) {
        showError(context.getString(errorMessageId))
    }

    override fun showError(errorMessage: String) {
        tvToast.text = errorMessage
        val toast = Toast(context.applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.view = this
        toast.show()
    }
}
