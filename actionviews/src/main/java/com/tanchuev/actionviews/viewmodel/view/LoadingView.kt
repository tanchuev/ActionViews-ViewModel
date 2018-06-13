package com.tanchuev.actionviews.viewmodel.view

import android.view.View
import com.tanchuev.actionviews.viewmodel.utils.setVisibility
import com.tanchuev.actionviews.viewmodel.widget.SwipeRefreshLayout


/**
 * @author tanchuev
 */

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
