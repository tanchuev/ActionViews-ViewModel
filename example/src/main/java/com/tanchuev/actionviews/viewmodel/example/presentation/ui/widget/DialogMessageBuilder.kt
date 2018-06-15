package com.tanchuev.actionviews.viewmodel.example.presentation.ui.widget

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.tanchuev.actionviews.viewmodel.example.presentation.R

class DialogMessageBuilder : AlertDialog.Builder {

    private var hasOnShowListener: Boolean = false

    constructor(context: Context) : super(context)

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    override fun show(): AlertDialog {
        val dialog = create()
        if (!hasOnShowListener) {
            dialog.setOnShowListener {
                val negativeButton = dialog.getButton(BUTTON_NEGATIVE)
                val positiveButton = dialog.getButton(BUTTON_POSITIVE)

                negativeButton.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
                positiveButton.setBackgroundColor(Color.RED)
            }
            hasOnShowListener = true
        }
        dialog.show()
        return dialog
    }
}