package com.tanchuev.actionviews.viewmodel.widget

import android.content.Context
import android.util.AttributeSet

import com.tanchuev.actionviews.viewmodel.view.LoadingView

/**
 * Created by tanchuev
 */

//todo это можно сделать через annotation processor
// просто ставим аннотацию над стандартной вьюшкой и генерируем код как в этом классе

class SwipeRefreshLayout : android.support.v4.widget.SwipeRefreshLayout, LoadingView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
}
