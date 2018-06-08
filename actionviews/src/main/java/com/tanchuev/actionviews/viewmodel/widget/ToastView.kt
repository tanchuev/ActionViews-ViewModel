package com.tanchuev.actionviews.viewmodel.widget

import android.content.Context
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.l_toast_view.view.*
import com.tanchuev.actionviews.viewmodel.R
import com.tanchuev.actionviews.viewmodel.view.ErrorView
import com.tanchuev.actionviews.viewmodel.utils.ErrorMessage

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

    override fun showMessage(errorMessage: ErrorMessage) {
        tvToast.text = if (errorMessage.errorMessage != null) {
            errorMessage.errorMessage.toString()
        } else {
            context.getString(errorMessage.errorMessageId!!.toInt())
        }
        val toast = Toast(context.applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.view = this
        toast.show()
    }
}
