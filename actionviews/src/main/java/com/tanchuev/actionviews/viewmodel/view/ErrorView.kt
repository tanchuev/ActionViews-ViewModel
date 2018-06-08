package com.tanchuev.actionviews.viewmodel.view

import com.tanchuev.actionviews.viewmodel.utils.ErrorMessage

/**
 * @author tanchuev
 */
interface ErrorView {
    fun showMessage(errorMessage: ErrorMessage)

    //метода hide нет, потому, что он не нужен.
    //сокрытие view такого типа должно контролироваться на уровне view
}
