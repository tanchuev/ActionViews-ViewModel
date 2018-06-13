package com.tanchuev.actionviews.viewmodel.widget

import android.content.Context
import android.util.AttributeSet

import com.tanchuev.actionviews.viewmodel.view.LoadingView

/**
 * Created by tanchuev
 */

class SwipeRefreshLayout : android.support.v4.widget.SwipeRefreshLayout, LoadingView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
}
