package com.tanchuev.actionviews.viewmodel.view

import android.view.View
import com.tanchuev.actionviews.viewmodel.utils.setVisibility
import com.tanchuev.actionviews.viewmodel.widget.SwipeRefreshLayout


/**
 * @author tanchuev
 */

// не во всех случаях есть возможность установить id для loadingView и в некоторых случаях loadingView == mainView
// todo пока что обработаю только первые три кейса, над четвертым буду думать только в том случае,
// todo когда настанет тот момент, что мне нужно будет отображать 2 loading view на экране
// есть 4 кейса в работе с loadingView
// 1 - который отображается и скрывает mainView и все action view, показывает mainView
// 2 - loadingView == mainView == swipeRefreshLayout - скрывает все action view, показывает ничего, т.к. main view итак отображается
// 3 - диалог загрузки, который отображается поверх mainView и скрывает все action view, показывает ничего, т.к. main view итак отображается
// 4 - две разные loading view на экране - нужен ли вообще этот кейс? может его как-то обходить?

// используется в тех случаях, когда LoadingView
// во время своего показа должна скрыть все actionViews,
// во время своего скрытия должна отобразить ничего, т.к. main view итак отображается
interface LoadingView {
    fun setVisibility(visible: Boolean, contentView: View) {
        val view = this as View
        if (visible) {
            when (this) {
                is SwipeRefreshLayout -> {
                    setVisibility(View.VISIBLE, this)
                    view.post {
                        this.isRefreshing = true
                    }
                }
                is TopLoadingView -> {
                    setVisibility(View.GONE, contentView)
                    setVisibility(View.VISIBLE, view)
                }
                else -> {
                    setVisibility(View.VISIBLE, view)
                }
            }

        } else {
            when (this) {
                null -> throw NullPointerException("LoadingView is null")
                is SwipeRefreshLayout -> this.post {
                    this.isRefreshing = false
                }
                is TopLoadingView -> {
                    setVisibility(View.VISIBLE, contentView)
                    setVisibility(View.GONE, view)
                }
                else -> {
                    setVisibility(View.GONE, view)
                }
            }
        }
    }
}
